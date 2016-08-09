package controllers;

import com.google.gson.Gson;
import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import phone.comparephone.CompareCart;

import phone.iphone.Souvenir;
import phone.iphone.IphoneJDBCTemplate;

@Controller
public class ControllerIphone {

    @Autowired
    IphoneJDBCTemplate iphoneJDBCTemplate;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public ModelAndView userSorexInfo(HttpSession session) {
        ModelAndView mv = new ModelAndView("index_iphone");
        session.setAttribute("page", "index");

        return mv;
    }

    @RequestMapping(value = "/iphones", method = RequestMethod.GET)
    public ModelAndView iphones(HttpSession session) {
        ModelAndView mv = new ModelAndView("ru_all_iphone");

        List<Souvenir> listSouvenir = iphoneJDBCTemplate.getListSouvenir();
        mv.addObject("listSouvenir", listSouvenir);
        
         ArrayList listTitle=new ArrayList();
        for(int i=0;i<listSouvenir.size();i++)
             listTitle.add(listSouvenir.get(i).getTitle());
        
        mv.addObject("listTitle", listTitle);
        
        session.setAttribute("page", "iphones");
        return mv;
    }

    @RequestMapping(value = "/iphone-{id}", method = RequestMethod.GET)
    public ModelAndView iphine(@PathVariable("id") String id, HttpSession session) {
        ModelAndView mv = new ModelAndView("ru_one_iphone");
        Souvenir souvenir= iphoneJDBCTemplate.getSouvenir(id);
        mv.addObject("souvenir", souvenir);
        session.setAttribute("page", "iphone-" + id);
        return mv;
    }

    /// ------------ Compare : Start ----------------- 
    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    public ModelAndView compare(HttpSession session) {
        ModelAndView mv = new ModelAndView("ru_compare");

       /* CompareCart compareCart = (CompareCart) session.getAttribute("compareCart");
        if (compareCart == null) {
            compareCart = new CompareCart();
        }*/
            List<Souvenir> listSouvenir = iphoneJDBCTemplate.getListSouvenir();
            mv.addObject("objects", listSouvenir);
        //mv.addObject("compareCart", compareCart);
        session.setAttribute("page", "compare");
        return mv;
    }

    @RequestMapping("/add-product-to-compare-list")
    public String addProductToCompareList(@RequestParam(value = "id") String id, HttpSession session) {
//            @RequestParam(value = "page") Integer page,           
        CompareCart compareCart = (CompareCart) session.getAttribute("compareCart");
        if (compareCart == null) {
            compareCart = new CompareCart();
        }

        Souvenir iphone = iphoneJDBCTemplate.getSouvenir(id);
       // compareCart.addItem(iphone);
        session.setAttribute("compareCart", compareCart);

        String page;
        try {
            page = (String) session.getAttribute("page");
        } catch (Exception e) {
            page = "index";
        }
        return "redirect:/" + page + ".htm";
    }
    
    
    @RequestMapping(value="/filter",method=RequestMethod.GET,produces={"text/html; charset=UTF-8"})
    public @ResponseBody String filter(@RequestParam String from,@RequestParam String to) {//@ResponseBody чтобы не было перенаправление
        if(from.isEmpty()) from="0"; 
        if(to.isEmpty()) to="2147483647";
        List<Souvenir> listSouvenir1=iphoneJDBCTemplate.getListSouvenir();
        Iterator<Souvenir> iter = listSouvenir1.iterator();
        while(iter.hasNext()){
            Souvenir next = iter.next();          
            if((next.getPrice()<Integer.parseInt(from) || next.getPrice()<Integer.parseInt(to)))
		iter.remove();
	}
        /*
        if(!title.isEmpty()){
        Iterator<Souvenir> iterTitle = listSouvenir1.iterator();
         while(iterTitle.hasNext()){
            Souvenir next = iterTitle.next();          
            if(!next.getTitle().equals(title))
		iterTitle.remove();
         }
	}*/
        
            
        return new Gson().toJson(listSouvenir1);
    }

    @RequestMapping("/admin")
    public ModelAndView admin(HttpServletRequest request) {
        if (request.getParameter("username").equals("admin")) {
            ModelAndView mv = new ModelAndView("/admin");
            List<Souvenir> listSouvenir = iphoneJDBCTemplate.getListSouvenir();
            mv.addObject("objects", listSouvenir);
            return mv;
        } else {
            return new ModelAndView("/index_iphone");
        }
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        ModelAndView mv = new ModelAndView("/admin");
        if (!multipartFile.isEmpty()) {
            try {
                HSSFWorkbook myExcelBook = new HSSFWorkbook(new ByteArrayInputStream(multipartFile.getBytes())) ;
                HSSFSheet myExcelSheet = myExcelBook.getSheet("Лист1");
             
                Map namedParameters = new HashMap(); 
                namedParameters.put("id", myExcelSheet.getRow(0).getCell(1).getStringCellValue());
                namedParameters.put("title", myExcelSheet.getRow(1).getCell(1).getStringCellValue());
                namedParameters.put("lacquer", myExcelSheet.getRow(2).getCell(1).getStringCellValue());
                namedParameters.put("fastening",  myExcelSheet.getRow(3).getCell(1).getStringCellValue());
                namedParameters.put("bevel", myExcelSheet.getRow(4).getCell(1).getStringCellValue());
                namedParameters.put("length",  myExcelSheet.getRow(5).getCell(1).getNumericCellValue());
                namedParameters.put("weight", myExcelSheet.getRow(6).getCell(1).getNumericCellValue());
                namedParameters.put("thickness", myExcelSheet.getRow(7).getCell(1).getNumericCellValue());
                namedParameters.put("price",myExcelSheet.getRow(8).getCell(1).getNumericCellValue());
                namedParameters.put("photo1", myExcelSheet.getRow(9).getCell(1).getStringCellValue());
                namedParameters.put("photo2",myExcelSheet.getRow(10).getCell(1).getStringCellValue());
                namedParameters.put("photo3", myExcelSheet.getRow(11).getCell(1).getStringCellValue());
                namedParameters.put("photo4",myExcelSheet.getRow(12).getCell(1).getStringCellValue());   
                namedParameters.put("description", myExcelSheet.getRow(13).getCell(1).getStringCellValue());
                
                List<Souvenir> listSouvenirTest = iphoneJDBCTemplate.getListSouvenir();
                boolean uniqueKey=true;
                for(int i=0;i<listSouvenirTest.size();i++){
                    if(listSouvenirTest.get(i).getId().equals(myExcelSheet.getRow(0).getCell(1).getStringCellValue())) uniqueKey=false;
                }
                
                if(uniqueKey){
                    iphoneJDBCTemplate.setSouvenir(namedParameters);
                }
                
                List<Souvenir> listSouvenir = iphoneJDBCTemplate.getListSouvenir();
                mv.addObject("objects", listSouvenir);
                return mv;
                
            } catch (IOException ex) {
                Logger.getLogger(ControllerIphone.class.getName()).log(Level.SEVERE, null, ex);
                return mv;
            }
        } 
    return mv;
    }


}
  /*
            ModelAndView mv = new ModelAndView("/ru_all_iphone");
            List<Souvenir> listSouvenir1=iphoneJDBCTemplate.getListSouvenir();
            String reqFrom=request.getParameter("from");
            String reqTo=request.getParameter("to");
            if(reqFrom.isEmpty()) reqFrom="0";
            if(reqTo.isEmpty()) reqTo="2147483647;
            for(int i=0;i<listSouvenir1.size();i++){
            if(listSouvenir1.get(i).getPrice()<Integer.parseInt(reqFrom) || listSouvenir1.get(i).getPrice()>Integer.parseInt(reqTo))
                listSouvenir1.remove(i);//????????????????????????
                i--;
            }      
            mv.addObject("listSouvenir", listSouvenir1);
            return mv;*/
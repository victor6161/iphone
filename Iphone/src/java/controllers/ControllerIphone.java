package controllers;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import phone.comparephone.CompareCart;
import phone.iphone.DB;
import phone.iphone.Iphone;
import phone.iphone.IphoneJDBCTemplate;

@Controller
public class ControllerIphone {

    @Autowired
    IphoneJDBCTemplate iphoneJDBCTemplate;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public ModelAndView userSorexInfo(HttpSession session) {
        ModelAndView mv = new ModelAndView("index_iphone");

//     List<Hmc> listHmc = hmcJDBCTemplate.listHmc();
//        printInFile(listHmc.toString());
//        mv.addObject("listHmc", listHmc);
        session.setAttribute("page", "index");

        return mv;
    }

    @RequestMapping(value = "/iphones", method = RequestMethod.GET)
    public ModelAndView iphones(HttpSession session) {
        ModelAndView mv = new ModelAndView("ru_all_iphone");

        List<Iphone> listIphone = iphoneJDBCTemplate.getListIphone();
//////        printInFile(listHmc.toString());
        mv.addObject("listIphone", listIphone);
        session.setAttribute("page", "iphones");
//
//                mv.addObject("kol_vo_page", 11);
////        mv.addObject("kol_vo_prod", vmcJDBCTemplate.getKol_vo_product());
//        mv.addObject("page_nomber", 1);
//        mv.addObject("kol_vo_on_page", 6);

        return mv;
    }

    @RequestMapping(value = "/iphone-{id}", method = RequestMethod.GET)
    public ModelAndView iphine(@PathVariable("id") int id, HttpSession session) {

//     ModelAndView mv = new ModelAndView("one_iph");
        ModelAndView mv = new ModelAndView("ru_one_iphone");
        Iphone iphone = iphoneJDBCTemplate.getIphone(id);
//        printInFile("HMC exeption2.txt", hmc.toString());
        mv.addObject("iphone", iphone);
        session.setAttribute("page", "iphone-" + id);

        return mv;
    }

    /// ------------ Compare : Start ----------------- 
    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    public ModelAndView compare(HttpSession session) {
        ModelAndView mv = new ModelAndView("ru_compare");

        CompareCart compareCart = (CompareCart) session.getAttribute("compareCart");
        if (compareCart == null) {
            compareCart = new CompareCart();
        }

        mv.addObject("compareCart", compareCart);

        session.setAttribute("page", "compare");
        return mv;
    }

    @RequestMapping("/add-product-to-compare-list")
    public String addProductToCompareList(@RequestParam(value = "id") int id, HttpSession session) {
//            @RequestParam(value = "page") Integer page,           
        CompareCart compareCart = (CompareCart) session.getAttribute("compareCart");
        if (compareCart == null) {
            compareCart = new CompareCart();
        }

        Iphone iphone = iphoneJDBCTemplate.getIphone(id);
        compareCart.addItem(iphone);
        session.setAttribute("compareCart", compareCart);

        String page;
        try {
            page = (String) session.getAttribute("page");
        } catch (Exception e) {
            page = "index";
        }
        return "redirect:/" + page + ".htm";
    }

    /*
   @RequestMapping("/admin")
    public String main(){
        
        return "admin";
        
    }*/
    @RequestMapping("/admin")
    public ModelAndView admin(HttpServletRequest request) {
        if (request.getParameter("username").equals("admin")) {
            ModelAndView mv = new ModelAndView("/admin");
            List<Iphone> listIphone = iphoneJDBCTemplate.getListIphone();
            mv.addObject("objects", listIphone);
            return mv;
        } else {
            return new ModelAndView("/index_iphone");
        }
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String name = null;

        if (!multipartFile.isEmpty()) {
            try {
                HSSFWorkbook myExcelBook = new HSSFWorkbook(new ByteArrayInputStream(multipartFile.getBytes())) ;
                HSSFSheet myExcelSheet = myExcelBook.getSheet("Лист1");
                HSSFRow row = myExcelSheet.getRow(0);
                row.getCell(0).getStringCellValue();
                //File file = new File("C:\\Users\\я\\Desktop\\work folder\\Новая (2)\\IPhone (example)\\IPhone\\1.txt");
                //multipartFile.transferTo(file);
               // FileWriter fw=new FileWriter(file,true);
               // fw.write("aaa");
               // fw.close();
                // File convFile = new File(multipartFile.getOriginalFilename());
                 //convFile.createNewFile(); 
                 //FileOutputStream fos = new FileOutputStream(convFile); 
                 //fos.write(multipartFile.getBytes());
                //String s2 = new String(multipartFile.getBytes());
                 
                return row.getCell(0).getStringCellValue();
            } catch (IOException ex) {
                Logger.getLogger(ControllerIphone.class.getName()).log(Level.SEVERE, null, ex);
                return "failed";
            }
        } else {
            return "file empty";
        }

    }

    /*
    @RequestMapping(value="/admin",method=RequestMethod.POST)
    public ModelAndView admin(@ModelAttribute User user){
       ModelAndView modelAndView=new ModelAndView();
       modelAndView.setViewName("main");
       modelAndView.addObject("user", user);
       return modelAndView;
       
   }*/
}

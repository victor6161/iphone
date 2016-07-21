package controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import phone.comparephone.CompareCart;
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
////        printInFile(listHmc.toString());
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

   @RequestMapping("/admin")
    public String main(){
        return "admin";
    
    }/*
    @RequestMapping(value="/admin",method=RequestMethod.POST)
    public ModelAndView admin(@ModelAttribute User user){
       ModelAndView modelAndView=new ModelAndView();
       modelAndView.setViewName("main");
       modelAndView.addObject("user", user);
       return modelAndView;
       
   }*/
}

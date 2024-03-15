package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller("productController")
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;

	@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	public ProductController() {
		System.out.println(":: ProductController default Contrctor call : " + this.getClass());
	}

//	@RequestMapping("/addProduct.do")
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, Model model) throws Exception {

		System.out.println("\n:: ==> addProduct().POST start......]");

		productService.addProduct(product);

		model.addAttribute("product", product);

		System.out.println("[addProduct().POST end......]\n");

		return "forward:/product/addProduct.jsp";
	}

	@RequestMapping(value = "getProduct/{prodNo}/{menu}", method = RequestMethod.GET)
	public String getProduct(@ModelAttribute("product") Product product, @PathVariable String menu,
			HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		System.out.println("\n:: ==> getProduct().GET start......]");
		System.out.println("ProductController.getProduct.manu : " + menu);

		String resultPath = "";
		String history = "";
		String cookieNewValue;

		product = productService.getProduct(product.getProdNo());
		model.addAttribute("product", product);

		for (Cookie cookie : request.getCookies()) {
			if (!cookie.getName().equals("history")) {
				cookie = new Cookie("history", "");
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}

		history = product.getProdNo() + ":" + product.getProdName().replaceAll(" ", "_") + "/";
		for (Cookie cookie : request.getCookies()) {
			System.out.println(cookie.getName());
			if (cookie.getName().equals("history")) {
				System.out.println("request.getCookies() : " + cookie.getValue());
				cookieNewValue = cookie.getValue().replaceAll(history, "");
				history += cookieNewValue;
				System.out.println("history= " + history);
				cookie.setValue(history);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
//		Cookie cookie = new Cookie("history", history);
//		cookie.setPath("/");
//		response.addCookie(cookie);

		model.addAttribute("menu", menu);

		resultPath = "forward:/product/getProduct.jsp";
		if (menu != null) {
			if (menu.equals("manage")) {
//				resultPath = "forward:/product/notUpdateProduct.jsp"; //´õ ÀÌ»ó ¾È¾¸
				resultPath = "forward:/product/updateProduct.jsp";
			}
		}

		System.out.println("[getProduct().GET end......]\n");
		return resultPath;
	}

	@RequestMapping(value = "listProduct/{menu}")
	public String listProduct(@ModelAttribute("search") Search search, @PathVariable String menu, Model model)
			throws Exception {

		System.out.println("\n:: ==> listProduct().GET/POST start......]");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}

		search.setPageSize(pageSize);

		Map<String, Object> map = productService.getProductList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println("listProduct.resultPage ::" + resultPage);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		model.addAttribute("menu", menu);

		System.out.println("[listProduct().GET/POST end......]\n");

		return "forward:/product/listProduct.jsp";
	}

	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, Model model) throws Exception {

		System.out.println("\n:: ==> updateProduct().POST start......]");

		productService.updateProduct(product);

		model.addAttribute("product", productService.getProduct(product.getProdNo()));

		System.out.println("[updateProduct().POST end......]\n");

		return "redirect:getProduct/" + product.getProdNo() + "/manage_search";
	}

//	@RequestMapping("/updateProductView.do")
//	public String updateProductView(@ModelAttribute("product") Product product, Model model) throws Exception {
//
//		System.out.println("\n:: ==> updateProductView() start......]");
//
//		model.addAttribute("product", productService.getProduct(product.getProdNo()));
//
//		System.out.println("[updateProductView() end......]\n");
//
//		return "forward:/product/updateProduct.jsp";
//	}

//	@RequestMapping("/updateQuantity.do")
//	public String updateQuantity(@ModelAttribute("product") Product product, @ModelAttribute("search") Search search,
//			Model model) throws Exception {
//
//		System.out.println("\n:: ==> updateQuantity() start......]");
//
//		productService.updateQuantity(product);
//
//		model.addAttribute("search", search);
//
//		System.out.println("[updateQuantity() end......]\n");
//
//		return "forward:/listProduct.do?menu=manage";
//	}

}

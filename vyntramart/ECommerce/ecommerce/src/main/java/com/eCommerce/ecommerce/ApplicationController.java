package com.eCommerce.ecommerce;


import com.eCommerce.ecommerce.model.*;
import com.eCommerce.ecommerce.service.OrdersService;
import com.eCommerce.ecommerce.service.ProductService;
import com.eCommerce.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    OrdersService ordersService;

    @RequestMapping("/")
    public String home(ModelMap modelMap){
        Long min_rating = 3L;
        List<Product> top_products = productService.findByProductRatingGreaterThanEqual(min_rating);
        modelMap.addAttribute("top_products", top_products);
        return "home";
    }


    // Login and Registration Controller
    @GetMapping("/login")
    public String logIn()
    {
        return "login";
    }

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@RequestParam("username") String name, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("phone") String contactNumber, ModelMap modelMap)
    {
        User check1 = userService.findByUserEmail(email);
        User check2 = userService.findByUserContactNumber(contactNumber);

        if(check1 == null && check2 == null){
            User newUser = new User(name, contactNumber, email, password);
            userService.saveUser(newUser);
            return "login";
        }
        else if(check1 == null){
            modelMap.addAttribute("error", "ContactNumber is already taken!!");
            return "login";
        }
        else{
            modelMap.addAttribute("error", "Email is already taken!!");
            return "login";
        }
    }

    // User Controller
    @GetMapping("/get_user_billing_addresses")
    public @ResponseBody List<BillingAddress> get_user_billing_addresses(HttpServletRequest request, ModelMap modelMap){

        User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
        if(logged_in_user != null){
            return logged_in_user.getBillingAddressList();
        }
        else{
            return null;
        }
    }

    // Admin Controller

    @GetMapping("/admin_dashboard")
    public String admin_dashboard(){
        return "admin_dashboard";
    }

    @PostMapping("/admin_edits")
    public @ResponseBody String admin_edits(HttpServletRequest request) throws Exception {

        String result = "no";
        String action = request.getParameter("action");

        if(action.equals("save_product_type")){
            String TypeName = request.getParameter("TypeName");
            ProductType newProductType = new ProductType(TypeName);
            productService.saveType(newProductType);
            result = "yes";
        }
        else if(action.equals("save_product_brand")){
            String BrandName = request.getParameter("BrandName");
            ProductBrand newProductBrand = new ProductBrand(BrandName);
            productService.saveBrand(newProductBrand);
            result = "yes";
        }
        else{
            String productName = request.getParameter("ProductName");
            String stock = request.getParameter("Stock");
            String rating = request.getParameter("Rating");
            String price = request.getParameter("Price");
            String discount = request.getParameter("Discount");
            String image = request.getParameter("Image");
            String description = request.getParameter("Description");
            String productType_string = request.getParameter("ProductType");
            String productBrand_string = request.getParameter("ProductBrand");
            ProductType productType = productService.getProductType(productType_string);
            ProductBrand productBrand = productService.getProductBrand(productBrand_string);

            Product newProduct = new Product(productName, Long.parseLong(stock), Long.parseLong(rating), Double.parseDouble(price), Double.parseDouble(discount), image, description, productType, productBrand);
            productService.saveProduct(newProduct);
            result = "yes";
        }

        return result;
    }

    // Product Controller

    @RequestMapping("/product_description/{id}")
    public String product_description(@PathVariable String id,ModelMap modelMap){
        Long productId = Long.valueOf(id);
        Optional<Product> optional_product = productService.findByProductId(productId);
        if(optional_product.isPresent()){
            Product product = optional_product.get();
            modelMap.addAttribute("product", product);
        }
        return "product_description";
    }


    // Profile Controller

    @RequestMapping("/profile")
    public String profile(HttpServletRequest request, ModelMap modelMap){

        User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
        if(logged_in_user != null){
            System.out.println(logged_in_user.getUserName());
        }
        modelMap.addAttribute("user", logged_in_user);
        return "profile_page";
    }


    // Wishlist Controller

    @GetMapping("/add_to_wishlist/{productId}/{wishListId}")
    public @ResponseBody String add_to_wishlist(@PathVariable String productId, @PathVariable String wishListId,HttpServletRequest request, ModelMap modelMap){

        String result = "no";

        Optional<Product> optional_product = productService.findByProductId(Long.parseLong(productId));

        if(optional_product.isPresent()){
            Product product = optional_product.get();
            User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
            WishList wishList;

            if(logged_in_user != null){
                wishList = ordersService.getWishlistByUser(logged_in_user);
                if(wishList == null){
                    wishList = new WishList(logged_in_user);
                }
            }
            else{
                if(wishListId.equals("-1")){
                    wishList = new WishList();
                }
                else{
                    Optional<WishList> optionalWishList= ordersService.getByWishListId(Long.parseLong(wishListId));
                    wishList = optionalWishList.orElseGet(WishList::new); // nothing but optionalWishList.isPresent() checking and then creating wishlist object
                }
            }

            List<Product> productList = wishList.getProductList();
            productList.add(product);
            wishList.setProductList(productList);

            ordersService.saveWishList(wishList);
            result = "yes";

            if(logged_in_user == null){
                /*myboject = repository.save(myboject);repository.flush();*/
                modelMap.addAttribute("wishlist_id",wishList.getWishListId());
            }
        }
        else{
            return "Sorry! This product doesn't exists anymore!!";
        }

        return result;
    }

    @GetMapping("/add_wishlist_to_cart/{productId}")
    public @ResponseBody String add_wishlist_to_cart(@PathVariable String productId, HttpServletRequest request, ModelMap modelMap ){

        String result = "no";

        Optional<Product> optionalProduct = productService.findByProductId(Long.parseLong(productId));
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();

            User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());

            if(logged_in_user != null){
                WishList wishList = ordersService.getWishlistByUser(logged_in_user);
                if(wishList != null){
                    List<Product> productList = wishList.getProductList();
                    productList.remove(product);

                    if(productList.isEmpty()){
                        ordersService.deleteWishList(wishList);
                    }
                    else{
                        wishList.setProductList(productList);
                        ordersService.saveWishList(wishList);
                    }

                    Cart cart = ordersService.getCartByUser(logged_in_user);
                    if(cart == null){
                        cart = new Cart(logged_in_user);
                    }

                    productList = cart.getProductList();
                    productList.add(product);
                    cart.setProductList(productList);

                    ordersService.saveCart(cart);
                    result = "yes";

                }
                else{
                    return "Sorry! This wishlist doesn't exists anymore!!";
                }
            }
            else{
                return "Please Log in to manage your cart";
            }
        }
        else{
            return "Sorry! This product doesn't exists anymore!!";
        }

        return result;
    }

    @GetMapping("/add_all_wishlist_to_cart")
    public @ResponseBody String add_all_wishlist_to_cart(HttpServletRequest request, ModelMap modelMap){
        String result = "no";

        User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());

        if(logged_in_user != null){
            WishList wishList = ordersService.getWishlistByUser(logged_in_user);
            if(wishList != null){
                List<Product> productList = wishList.getProductList();

                Cart cart = ordersService.getCartByUser(logged_in_user);
                if(cart == null){
                    cart = new Cart(logged_in_user);
                }
                cart.setProductList(productList);

                ordersService.deleteWishList(wishList);
                ordersService.saveCart(cart);
                result = "yes";
            }
            else{
                return "Sorry! This wishlist doesn't exists anymore!!";
            }
        }
        else{
            return "Please Log in to manage your cart";
        }

        return result;
    }

    @GetMapping("/delete_from_wishlist/{productId}/{wishlistId}")
    public @ResponseBody String delete_from_wishlist(@PathVariable String productId, @PathVariable String wishlistId, HttpServletRequest request, ModelMap modelMap){
        String result = "no";

        Optional<Product> optionalProduct = productService.findByProductId(Long.parseLong(productId));

        if(optionalProduct.isPresent()){
            User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
            WishList wishList;

            if(logged_in_user != null){
                wishList = ordersService.getWishlistByUser(logged_in_user);
                if(wishList == null){
                    return "Sorry! This wishlist doesn't exists anymore!!";
                }
            }
            else{
                Optional<WishList> optionalWishList = ordersService.getByWishListId(Long.parseLong(wishlistId));

                if(optionalWishList.isPresent()){
                    wishList = optionalWishList.get();
                }
                else{
                    return "Sorry! This wishlist doesn't exists anymore!!";
                }
            }

            Product product = optionalProduct.get();

            List<Product> productList = wishList.getProductList();
            productList.remove(product);

            if(productList.isEmpty()){
                ordersService.deleteWishList(wishList);
                if(logged_in_user == null){
                    modelMap.addAttribute("wishlist_id","-1");
                }
            }
            else{
                wishList.setProductList(productList);
                ordersService.saveWishList(wishList);
            }
            result = "yes";
        }
        else{
            return "Sorry! This product doesn't exists anymore!!";
        }

        return result;
    }

    @GetMapping("/add_to_logged_wishlist/{wishlistId}")
    public @ResponseBody void add_to_logged_wishlist(@PathVariable String wishlistId, HttpServletRequest request, ModelMap modelMap){

        Optional<WishList> optionalAnonymousWishList = ordersService.getByWishListId(Long.parseLong(wishlistId));

        if(optionalAnonymousWishList.isPresent()){
            User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
            if(logged_in_user != null){
                WishList anonymousWishList = optionalAnonymousWishList.get();

                WishList wishList = ordersService.getWishlistByUser(logged_in_user);
                if(wishList == null){
                    wishList = new WishList(logged_in_user);
                }

                wishList.setProductList(anonymousWishList.getProductList());

                ordersService.deleteWishList(anonymousWishList);
                ordersService.saveWishList(wishList);
            }
        }
    }

    @GetMapping("/get_wishlist_products/{wishlistId}")
    public @ResponseBody List<Product> get_wishlist_products(@PathVariable String wishlistId, HttpServletRequest request, ModelMap modelMap){

        User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
        WishList wishList = null;

        if(logged_in_user != null){
            wishList = ordersService.getWishlistByUser(logged_in_user);
            return wishList.getProductList();
        }
        else{
            Optional<WishList> optionalWishList = ordersService.getByWishListId(Long.parseLong(wishlistId));

            if(optionalWishList.isPresent()){
                wishList = optionalWishList.get();
                return wishList.getProductList();
            }
        }
        return null;
    }

    // Cart controller

    @RequestMapping("/cart")
    public String cart(){
        return "cart";
    }


    @GetMapping("/add_to_cart/{productId}")
    public @ResponseBody String add_to_cart(@PathVariable String productId, HttpServletRequest request, ModelMap modelMap){
        String result = "no";

        User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
        if(logged_in_user != null){
            Optional<Product> optionalProduct = productService.findByProductId(Long.parseLong(productId));

            if(optionalProduct.isPresent()){
                Cart cart = ordersService.getCartByUser(logged_in_user);
                if(cart == null){
                    cart = new Cart(logged_in_user);
                }

                List<Product> productList = cart.getProductList();
                productList.add(optionalProduct.get());
                cart.setProductList(productList);

                ordersService.saveCart(cart);
                result = "yes";
            }
            else{
                return "Sorry! This product doesn't exists anymore!!";
            }
        }
        else{
            return  "Please Log in to manage your cart";
        }
        return result;
    }

    @GetMapping("/from_cart_to_wishlist/{productId}")
    public @ResponseBody String from_cart_to_wishlist(@PathVariable String productId, HttpServletRequest request, ModelMap modelMap){
        String result = "no";

        User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
        if(logged_in_user != null){
            Optional<Product> optionalProduct = productService.findByProductId(Long.parseLong(productId));

            if(optionalProduct.isPresent()){
                Product product = optionalProduct.get();

                Cart cart = ordersService.getCartByUser(logged_in_user);
                if(cart != null){
                    List<Product> productList = cart.getProductList();

                    if(productList.contains(product)){
                        productList.removeAll(Collections.singleton(product));
                        cart.setProductList(productList);

                        WishList wishList = ordersService.getWishlistByUser(logged_in_user);
                        if(wishList == null){
                            wishList = new WishList(logged_in_user);
                        }

                        productList = wishList.getProductList();
                        productList.add(product);
                        wishList.setProductList(productList);

                        ordersService.saveCart(cart);
                        ordersService.saveWishList(wishList);
                        result = "yes";
                    }
                    else{
                        return "Your Cart doesn't have this product! Please reload the page";
                    }
                }
                else{
                    return "Your Cart is empty! Please reload the page";
                }
            }
            else{
                return "Sorry! This product doesn't exists anymore!!";
            }
        }
        else{
            return  "Please Log in to manage your cart";
        }
        return result;
    }

    @GetMapping("/delete_from_cart/{productId}")
    public @ResponseBody String delete_from_cart(@PathVariable String productId, HttpServletRequest request, ModelMap modelMap){
        String result = "no";

        User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
        if(logged_in_user != null){
            Optional<Product> optionalProduct = productService.findByProductId(Long.parseLong(productId));

            if(optionalProduct.isPresent()){
                Cart cart = ordersService.getCartByUser(logged_in_user);
                if(cart != null){
                    List<Product> productList = cart.getProductList();

                    if(productList.contains(optionalProduct.get())){
                        productList.removeAll(Collections.singleton(optionalProduct.get()));
                        cart.setProductList(productList);

                        ordersService.saveCart(cart);
                        result = "yes";
                    }
                    else{
                        return "Your Cart doesn't have this product! Please reload the page";
                    }
                }
                else{
                    return "Your Cart is empty! Please reload the page";
                }
            }
            else{
                return "Sorry! This product doesn't exists anymore!!";
            }
        }
        else{
            return  "Please Log in to manage your cart";
        }
        return result;
    }


    @GetMapping("/get_cart_products")
    public @ResponseBody List<Product> get_Cart_products(HttpServletRequest request, ModelMap modelMap){

        User logged_in_user = userService.findByUserUserName(request.getUserPrincipal().getName());
        if(logged_in_user != null){
            Cart cart = ordersService.getCartByUser(logged_in_user);

            if(cart != null){
                return cart.getProductList();
            }
        }
        else{
            return  null;
        }
        return null;
    }


    //Product Controller

    @GetMapping("/get_all_types")
    public @ResponseBody List<ProductType> get_all_types(HttpServletRequest request, ModelMap modelMap){
        return productService.getAllTypes();
    }

    @GetMapping("/get_all_brands")
    public @ResponseBody List<ProductBrand> get_all_brands(HttpServletRequest request, ModelMap modelMap){
        return productService.getAllBrands();
    }



}

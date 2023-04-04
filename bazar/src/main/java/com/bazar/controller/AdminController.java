package com.bazar.controller;

import com.bazar.dto.ProductDto;
import com.bazar.model.Category;
import com.bazar.model.Product;
import com.bazar.services.CategoryService;
import com.bazar.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;



@Controller
public class AdminController {
    public static  String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

//  public  AdminController(CategoryService categoryService){
//        this.categoryService=categoryService;
//    }
@GetMapping("/admin")
public String adminHome(){
    return "adminHome";
}
      @GetMapping("/admin/categories")
      public String getCat(Model model){
          List<Category> categories = categoryService.getAllCategory();
          model.addAttribute("categories",categories);
           return "categories";
}
      @GetMapping("/admin/categories/add")
      public String getCatAdd(Model model){
      model.addAttribute("category",new Category());
      return "categoriesAdd";
    }
      @PostMapping("/admin/categories/add")
      public String postCatAdd(@ModelAttribute("category") Category category){
      categoryService.addCategory(category);
      return "redirect:/admin/categories";
     }
     @GetMapping("/admin/categories/delete/{id}")
      public String deleteCat(@PathVariable int id){
      categoryService.deleteCategoryById(id);
      return "redirect:/admin/categories";
     }
     @GetMapping("/admin/categories/update/{id}")
      public String updateCat(@PathVariable int id,Model model) {
         Optional<Category> category = categoryService.getCategoryById(id);
         if (category.isPresent()) {
             model.addAttribute("category", category);
             return "categoriesAdd";
         } else {
             return "404";
         }
     }
//     product section--------------------------------------------------------------------------------------------------------

    @GetMapping("/admin/products")
    public String getAllProduct(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products",products);
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String productAddGet(Model model){
      // ProductDto productDTO=new ProductDto();
        model.addAttribute("productDTO",new ProductDto());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String ProductAddPost(@ModelAttribute("productDTO") ProductDto productDto ,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {
    Product product=new Product();
    product.setId(productDto.getId());
    product.setName(productDto.getName());
    product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
    product.setPrice(productDto.getPrice());
    product.setWeight(productDto.getWeight());
    String imageUUID;
    if (!file.isEmpty()){
        imageUUID=file.getOriginalFilename();
        Path fileNameAndPath= Paths.get(uploadDir,imageUUID);
        Files.write(fileNameAndPath,file.getBytes());
    }else {
        imageUUID=imgName;
    }
    product.setImageName(imageUUID);
    productService.addProduct(product);

    return "redirect:/admin/products";
    }


    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id,Model model) {

    Product product=productService.getProductbyId(id).get();

    ProductDto productDto=new ProductDto();
    productDto.setId(product.getId());
    productDto.setName(product.getName());
    productDto.setCategoryId(product.getCategory().getId());
    productDto.setPrice(product.getPrice());
    productDto.setWeight(product.getWeight());
    productDto.setDescription(product.getDescription());
    productDto.setImageName(product.getImageName());

    model.addAttribute("categories",categoryService.getAllCategory());
    model.addAttribute("productDTO",productDto);
        return "productsAdd";
    }
}

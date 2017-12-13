package controller;

import db.Hdb;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

public class Controller
{
    @Autowired
    private static Hdb.Buyer dbBuyer = new Hdb.Buyer();
    @Autowired
    private static Hdb.Discount dbDiscount = new Hdb.Discount();
    @Autowired
    private static Hdb.Product dbProduct = new Hdb.Product();
    @Autowired
    private static Hdb.Sale dbSale = new Hdb.Sale();
    @Autowired
    private static Hdb.Seller dbSeller = new Hdb.Seller();

    @RequestMapping(method = RequestMethod.GET, value = "/getAllBuyers")
    public static @ResponseBody
    List<Buyer> getAllBuyerrs(){
        List<Buyer> buyers = dbBuyer.getAll();
        return buyers;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateBuyer")
    public static ResponseEntity updateBuyer(
            @RequestParam(value = Buyer.ID_VALUE) Integer buyerId,
            @RequestParam(value = Buyer.FIRST_NAME_VALUE) String firstName,
            @RequestParam(value = Buyer.MIDDLE_NAME_VALUE) String middleName,
            @RequestParam(value = Buyer.LAST_NAME_VALUE) String lastName,
            @RequestParam(value = Buyer.BIRTH_DATE_VALUE) Long birthDate,
            @RequestParam(value = Buyer.LIVING_ADDRESS_VALUE) String livingAddress,
            @RequestParam(value = Buyer.PHONE_NUMBER_VALUE) String phoneNumber){
        try{
            Buyer updatedBuyer = dbBuyer.getBuyerById(buyerId);
            updatedBuyer.setFirstName(firstName);
            updatedBuyer.setMiddleName(middleName);
            updatedBuyer.setLastName(lastName);
            updatedBuyer.setBirthDate(new Date(birthDate));
            updatedBuyer.setLivingAddress(livingAddress);
            updatedBuyer.setPhoneNumber(phoneNumber);
            dbBuyer.updateBuyer(updatedBuyer);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addBuyer")
    public static ResponseEntity addBuyer(@RequestParam(value = Buyer.FIRST_NAME_VALUE) String firstName,
                                   @RequestParam(value = Buyer.MIDDLE_NAME_VALUE) String middleName,
                                   @RequestParam(value = Buyer.LAST_NAME_VALUE) String lastName,
                                   @RequestParam(value = Buyer.BIRTH_DATE_VALUE) Long birthDate,
                                   @RequestParam(value = Buyer.LIVING_ADDRESS_VALUE) String livingAddress,
                                   @RequestParam(value = Buyer.PHONE_NUMBER_VALUE) String phoneNumber){
        Buyer buyer = new Buyer(firstName,middleName,lastName,new Date(birthDate),phoneNumber,livingAddress);
        try{
            dbBuyer.addBuyer(buyer);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteBuyer")
    public static ResponseEntity deleteBuyer(@RequestParam(value = Buyer.ID_VALUE) Integer buyerId){
        try{
            Buyer deleted = dbBuyer.getBuyerById(buyerId);
            dbBuyer.removeBuyer(deleted);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getProducts")
    public static  @ResponseBody List<Product> getAllProducts(){
        List<Product> products =dbProduct.getAll();
        return products;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveProduct")//++
    public static ResponseEntity saveProduct(
            @RequestParam(value = Product.NAME_VALUE) String productName,
            @RequestParam(value = Product.UNIT_COAST_VALUE) Integer unitCoast,
            @RequestParam(value = Product.UNIT_NAME_VALUE) String unitName,
            @RequestParam(value = Product.SALE_ID) Integer saleId ){
        try{
            Sale sale = dbSale.getSaleById(saleId);
            Product product = new Product(productName,unitCoast,unitName,sale);
            dbProduct.saveProducr(product);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateProduct")
    public static ResponseEntity updateProduct(
            @RequestParam(value = Product.ID_VALUE) Integer productId,
            @RequestParam(value = Product.NAME_VALUE) String productName,
            @RequestParam(value = Product.UNIT_COAST_VALUE) Integer unitCoast,
            @RequestParam(value = Product.UNIT_NAME_VALUE) String unitName){
        try{
            Product updatedProduct =dbProduct.findProductById(productId);
            updatedProduct.setName(productName);
            updatedProduct.setUnitCoast(unitCoast);
            updatedProduct.setUnitName(unitName);
            dbProduct.update(updatedProduct);
        }
        catch (Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/removeProduct")
    public static ResponseEntity deleteProduct( @RequestParam(value = Product.ID_VALUE) Integer productId){
        try{
            Product product = dbProduct.findProductById(productId);
            dbProduct.delete(product);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getDiscounts")
    public static  @ResponseBody List<Discount> getAddDiscounts(){
        List<Discount> discounts = dbDiscount.getAllDisc();
        return discounts;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addDiscount")
    public static ResponseEntity addNewDiscount(
            @RequestParam(value = Discount.ACTUAL_FROM_VALUE) Long actualFrom,
            @RequestParam(value = Discount.ACTUAL_TO_VALUE) Long actualTo,
            @RequestParam(value = Discount.AMOUNT_DISCOUNT_VALUE) Integer amountDiscount,
            @RequestParam(value = Discount.PRODUCT_ID) Integer productId,
            @RequestParam(value = Discount.BUYER_ID) Integer buyerId){
        Product product = dbProduct.findProductById(productId);
        Buyer buyer = dbBuyer.getBuyerById(buyerId);
        Discount discount = new Discount(new Date(actualFrom),new Date(actualTo),amountDiscount,product,buyer);
        try {
            dbDiscount.addDiscount(discount);
        } catch (Exception e) {
            new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);//todo detached entity passed to persist - persist ot merge-fixed
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateDiscount")
    public static ResponseEntity updateDiscount(
            @RequestParam(value = Discount.ID_VALUE) Integer discountId,
            @RequestParam(value = Discount.ACTUAL_FROM_VALUE) Long newActualFromDate,
            @RequestParam(value = Discount.ACTUAL_TO_VALUE) Long newActualToDate,
            @RequestParam(value = Discount.AMOUNT_DISCOUNT_VALUE) Integer newAmountDiscount,
            @RequestParam(value = Discount.BUYER_ID) Integer buyerId,
            @RequestParam(value = Discount.PRODUCT_ID) Integer productId){
        try{
            Product product = dbProduct.findProductById(productId);
            Buyer buyer = dbBuyer.getBuyerById(buyerId);
            Discount updated = dbDiscount.findDiscountById(discountId);
            updated.setActualFrom(new Date(newActualFromDate));
            updated.setActualTo(new Date(newActualToDate));
            updated.setAmountDiscount(newAmountDiscount);
            updated.setBuyer(buyer);
            updated.setProduct(product);
            dbDiscount.updateDiscount(updated);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteDiscount")
    public static ResponseEntity deleteDiscount(@RequestParam(value = Discount.ID_VALUE) Integer discountId){
        Discount deleted = dbDiscount.findDiscountById(discountId);
        try{
            dbDiscount.deleteDiscount(deleted);
        }
        catch (Throwable e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getAllSells")
    public static  @ResponseBody List<Sale> getAllSells(){
        return dbSale.getAllSells();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addSale")
    public static ResponseEntity<Throwable> addSale(@RequestParam(value = Sale.ORDER_DATE_VALUE) Long orderDate,
                                             @RequestParam(value = Sale.DELIVERY_DATE_VALUE) Long deliveryDate,
                                             @RequestParam(value = Sale.AMOUNT_PRODUCT_VALUE) Integer amountProduct,
                                             @RequestParam(value = Sale.SELECTED_BUYER_ID) Integer buyerId) {
        Buyer selectedBuyer = dbBuyer.getBuyerById(buyerId);
        Sale sale = new Sale(new Date(orderDate),new Date(deliveryDate),amountProduct,selectedBuyer);
        try {
            dbSale.addSale(sale);
        }
        catch (Throwable e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateSale")
    public static ResponseEntity updateSale(@RequestParam(value = Sale.ID_VALUE) Integer saleId,
                                     @RequestParam(value = Sale.AMOUNT_PRODUCT_VALUE) Integer amountProduct,
                                     @RequestParam(value = Sale.ORDER_DATE_VALUE) Long orderDate,
                                     @RequestParam(value = Sale.DELIVERY_DATE_VALUE) Long deliveryDate){
        try{
            Sale existingSale = dbSale.getSaleById(saleId);
            existingSale.setDeliveryDate(new Date(deliveryDate));
            existingSale.setOrderDate(new Date(orderDate));
            existingSale.setAmountProduct(amountProduct);
            dbSale.updateSale(existingSale);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteSale")
    public static ResponseEntity deleteSale(@RequestParam(value = Sale.ID_VALUE) Integer saleId){
        try{
            Sale deletedSale = dbSale.getSaleById(saleId);
            dbSale.deleteSale(deletedSale);
        }
        catch (Throwable ex){
            return new ResponseEntity(ex,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllSellers")//+
    public static  @ResponseBody List<Seller> getAllSellers(){
        return dbSeller.getAllSellers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addSeller")
    public static ResponseEntity addSeller(@RequestParam(value = Seller.FIRST_NAME_VALUE) String firstName,
                                    @RequestParam(value = Seller.MIDDLE_NAME_VALUE) String middleName,
                                    @RequestParam(value = Seller.LAST_NAME_VALUE) String lastName,
                                    @RequestParam(value = Seller.BIRTH_DATE_VALUE) Long birthDate,
                                    @RequestParam(value = Seller.EMAIL_VALUE) String email,
                                    @RequestParam(value = Seller.DELIVERY_ADDRESS_VALUE) String deliveryAddress,
                                    @RequestParam(value = Seller.SALE_ID) Integer saleId){
        try{
            Sale sale = dbSale.getSaleById(saleId);
            Seller seller = new Seller(firstName,middleName,lastName,new Date(birthDate),email,deliveryAddress,sale);
            dbSeller.addSeller(seller);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateSeller")
    public static ResponseEntity updateSeller(@RequestParam(value = Seller.ID_VALUE) Integer id,
                                       @RequestParam(value = Seller.FIRST_NAME_VALUE) String firstName,
                                       @RequestParam(value = Seller.MIDDLE_NAME_VALUE) String middleName,
                                       @RequestParam(value = Seller.LAST_NAME_VALUE) String lastName,
                                       @RequestParam(value = Seller.BIRTH_DATE_VALUE) Long birthDate,
                                       @RequestParam(value = Seller.EMAIL_VALUE) String email,
                                       @RequestParam(value = Seller.DELIVERY_ADDRESS_VALUE) String deliveryAddress,
                                       @RequestParam(value = Seller.SALE_ID) Integer saleId){
        try{
            Seller updatedSeller = dbSeller.getSellerById(id);
            Sale selectedSale = dbSale.getSaleById(saleId);
            updatedSeller.setFirstName(firstName);
            updatedSeller.setLastName(lastName);
            updatedSeller.setMiddleName(middleName);
            updatedSeller.setBirthDate(new Date(birthDate));
            updatedSeller.setEmail(email);
            updatedSeller.setDeliveryAddress(deliveryAddress);
            updatedSeller.setSale(selectedSale);
            dbSeller.updateSeller(updatedSeller);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteSeller")
    public static ResponseEntity deleteSeller(@RequestParam(value = Seller.ID_VALUE) Integer sellerId){
        try{
            Seller deleted = dbSeller.getSellerById(sellerId);
            dbSeller.deleteSeller(deleted);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

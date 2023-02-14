package com.example.demo.api;

import com.example.demo.HttpController;
import com.example.demo.model.Product;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.springframework.web.bind.annotation.RestController;
import com.github.underscore.*;

import java.net.http.HttpResponse;
import java.util.*;

@RestController
public class ProductCatalogAPI {

    String operation;

    public String useCatalog(String operation) {
        String BaseURL = "http://localhost:9003/rest/";
        return BaseURL + operation;
    }

    public Product getProductByBarcode(String barcode) {
        HttpResponse<String> response = HttpController.getRequest(useCatalog("findByBarCode/" + barcode));
        HashMap<String, Object> map = (HashMap<String, Object>) U.fromXmlMap(response.body());
        HashMap<String, Object> productMap = (HashMap<String, Object>) map.get("product");
        if (productMap == null) {
            noSuchProductErrorBarcode(barcode);
            return null;
        } else {
            String name = String.valueOf(productMap.get("name"));
            return new Product(barcode, name);
        }
    }

    public void noSuchProductErrorBarcode(String barcode) {
        System.out.println("No product with barcode: " + barcode + " was found.");
    }

    public void noSuchProductErrorText(String name) {
        System.out.println("No product with name or keyword: " + name + " was found.");
    }

    public Product getProductByName(String text) {
        HttpResponse<String> response = HttpController.getRequest(useCatalog("findByName/" + text));
        HashMap<String, Object> map = (HashMap<String, Object>) U.fromXmlMap(response.body());
        HashMap<String, Object> productsMap = (HashMap<String, Object>) map.get("products");
        HashMap<String, Object> productMap = (HashMap<String, Object>) productsMap.get("product");
        if (productMap == null) {
            Product p=getProductByKeyword(text);
            if (p == null) {
                noSuchProductErrorText(text);
                return null;
            }
            else{
                return p;
            }
        } else {
            String barcode = String.valueOf(productMap.get("barCode"));
            String name = String.valueOf(productMap.get("name"));
            return new Product(barcode, name);
        }
    }

    public Product getProductByKeyword(String text){
        HttpResponse<String> response = HttpController.getRequest(useCatalog("findByKeyword/" + text));
        HashMap<String, Object> map = (HashMap<String, Object>) U.fromXmlMap(response.body());
        HashMap<String, Object> productsMap = (HashMap<String, Object>) map.get("products");
        HashMap<String, Object> productMap = (HashMap<String, Object>) productsMap.get("product");
        if (productMap == null) {
            noSuchProductErrorText(text);
            return null;
        }
        else{
            String barcode = String.valueOf(productMap.get("barCode"));
            String name = String.valueOf(productMap.get("name"));
            return new Product(barcode, name);
        }
    }
}

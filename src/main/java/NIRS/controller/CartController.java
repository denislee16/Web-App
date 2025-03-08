package NIRS.controller;

import NIRS.entity.Cars;
import NIRS.entity.CatalogOfService;
import NIRS.entity.PurchaseHistories;
import NIRS.service.CarsService;
import NIRS.service.CatalogOfServiceService;
import NIRS.service.PurchaseHistoriesService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CartController {
    @Autowired
    CatalogOfServiceService catalogOfServiceService;
    @Autowired
    private CarsService carsService;
    @Autowired
    PurchaseHistoriesService purchaseHistoriesService;

    @GetMapping("/cart/list")
    public @ResponseBody List<CartItem> getCart(HttpSession session) {
        Map<String, List<CartItem>> cartMap = (Map<String, List<CartItem>>) session.getAttribute("cart");
        List<CartItem> cartList = new ArrayList<>();
        if (cartMap != null) {
            for (List<CartItem> items : cartMap.values()) {
                cartList.addAll(items);
            }
        }
        return cartList;
    }

    @GetMapping("/cart")
    public String cart() {
        return "user/cart";
    }

    @PostMapping("/cart/placeOrder")
    public ResponseEntity<?> placeOrder(HttpSession session, @RequestBody ClientSelect clientSelect) {
        try {
            Map<String, List<CartItem>> cartMap = (Map<String, List<CartItem>>) session.getAttribute("cart");
            if (cartMap == null || cartMap.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Корзина пуста.");
            }
            Long client_id = clientSelect.getClient_id();
            //Long worker_id = workerSelect.getWorker_id();
            Long worker_id = 3L;
//            if (carId == null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не выбран автомобиль.");
//            }
            PurchaseHistories order = purchaseHistoriesService.createPurchaseFromCart(getCart(session), client_id, worker_id);
            session.removeAttribute("cart");
            return ResponseEntity.ok(Map.of("message", "Заказ успешно оформлен!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error placing order " + e.getMessage());
        }
    }

    @PostMapping("/addToCart")
    public ResponseEntity<Map<String, String>> addToCart(@RequestBody CartItem[] items, HttpSession session) {
        Map<String, List<CartItem>> cart = getCartFromSession(session);

        for (CartItem item : items) {
            if (item.isService()) {
                CatalogOfService service = catalogOfServiceService.readById(item.getId());
                CartItem sItem = new CartItem(service.getCatalog_id(), service.getName(), service.getCost(), 1, true);
                addItemToCart(cart, "ServiceCartItem", sItem);
            } else {
                Cars cars = carsService.readById(item.getId());
                CartItem pItem = new CartItem(cars.getCar_id(), cars.getModel(), cars.getCost(), 1, false);
                addItemToCart(cart, "CarsCartItem", pItem);
            }
        }
        session.setAttribute("cart", cart);
        return ResponseEntity.ok(Map.of("message", "Товары добавлены в корзину!"));
    }


    private Map<String, List<CartItem>> getCartFromSession(HttpSession session) {
        Map<String, List<CartItem>> cart = (Map<String, List<CartItem>>) session.getAttribute("cart");
        return cart != null ? cart : new HashMap<>();
    }

    private void addItemToCart(Map<String, List<CartItem>> cart, String type, CartItem newItem) {
        List<CartItem> cartItems = cart.computeIfAbsent(type, k -> new ArrayList<>());

        boolean itemExists = false;
//        for (CartItem existingItem : cartItems) {
//            if (existingItem.isService() == newItem.isService() && existingItem.getId().equals(newItem.getId())) {
//                existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
//                itemExists = true;
//                break;
//            }
//        }

        if (!itemExists) {
            cartItems.add(newItem);
        }
    }

    @Data
    static class ClientSelect {
        Long client_id;
    }


    @Data
    @AllArgsConstructor
    public static class CartItem {
        Long id;
        String name;
        double price;
        int quantity;
        boolean isService;
    }

}

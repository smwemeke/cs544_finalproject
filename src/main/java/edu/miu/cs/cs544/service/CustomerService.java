package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.dto.*;
import edu.miu.cs.cs544.dto.orders.PlaceOrderRequest;
import edu.miu.cs.cs544.dto.orders.OrderResponse;
import edu.miu.cs.cs544.dto.orders.StateChangeRequest;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto register(CustomerRegisterRequest request);
    OrderResponse placeOrder(PlaceOrderRequest request);


    boolean isAvailable(int productId);

    List<ProductResponse> listAllProduct(ListProductRequest request, boolean isAvailable);

    boolean checkIn(StateChangeRequest request);
    boolean cancel(StateChangeRequest request);
    boolean checkOut(StateChangeRequest request);

}

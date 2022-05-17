package pl.ticketsystem.ticketsystem.PayPal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {

    private double price;
    private String description;
    private LocalDateTime localDateTime;

}

package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.example.model.Tables.Status;

@Data
@NoArgsConstructor
public class TablesDTO {

    private Long tableId;
    private String restaurantUsername;
    private long tableNumber;
    private Status status;
    private String bookingId;

}
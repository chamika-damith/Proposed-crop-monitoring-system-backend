package com.example.proposedcropmonitoringsystembackend.customstatuscode;

import com.example.proposedcropmonitoringsystembackend.dto.CustomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessStatus implements CustomStatus {
    private int statusCode;
    private String statusMessage;
}

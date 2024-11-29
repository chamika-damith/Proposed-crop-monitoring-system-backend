package com.example.proposedcropmonitoringsystembackend.util;

import com.example.proposedcropmonitoringsystembackend.customstatuscode.ErrorStatus;
import com.example.proposedcropmonitoringsystembackend.dto.CustomStatus;
import com.example.proposedcropmonitoringsystembackend.dto.impl.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.regex.Pattern;

public class ValidateData {
    public static boolean isValid(String value, Pattern pattern) {
        return value != null && pattern.matcher(value).matches();
    }

    public static CustomStatus validateCropDTO(CropDTO cropDTO) {
        if (!isValid(cropDTO.getCommonName(), Regex.getNamePattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(),"Invalid Common Name");
        }
        if (!isValid(cropDTO.getScientificName(), Regex.getNamePattern())) {
            return new ErrorStatus( HttpStatus.BAD_REQUEST.value(),"Invalid Scientific Name");
        }
        if (!isValid(cropDTO.getCategory(), Regex.getNamePattern())) {
            return new ErrorStatus( HttpStatus.BAD_REQUEST.value(),"Invalid Category");
        }
        if (!isValid(cropDTO.getSeason(), Regex.getNamePattern())) {
            return new ErrorStatus( HttpStatus.BAD_REQUEST.value(),"Invalid Season");
        }
        return null;
    }

    public static CustomStatus validateEquipmentDTO(EquipmentDTO equipmentDTO) {
        if (!isValid(equipmentDTO.getName(), Regex.getNamePattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Invalid Equipment Name");
        }
        if (equipmentDTO.getEquipmentType() == null) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Equipment Type is required");
        }
        if (equipmentDTO.getStatus() == null) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Equipment Status is required");
        }
        if (equipmentDTO.getStaff() == null || !isValid(equipmentDTO.getStaff().getFirstName(), Regex.getNamePattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Invalid Staff Assignment");
        }
        if (equipmentDTO.getField() == null || !isValid(equipmentDTO.getField().getFieldName(), Regex.getNamePattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Invalid Field Assignment");
        }
        return null;
    }

    public static CustomStatus validateFieldDTO(FieldDTO field){
        if (!isValid(field.getFieldName(), Regex.getNamePattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Invalid Field Name");
        }
        if (field.getFieldLocation() == null || field.getFieldLocation().isEmpty()) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Field Location is required");
        }
        if (field.getFieldSize() <= 0) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Field Size must be greater than 0");
        }

        return null;
    }

    public static CustomStatus validateStaffDTO(StaffDTO staff){
        if (staff == null) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Staff Assignment is required");
        }

        if (!isValid(staff.getFirstName(), Regex.getNamePattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Invalid Staff First Name");
        }

        if (!isValid(staff.getLastName(), Regex.getNamePattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Invalid Staff Last Name");
        }

        if (staff.getDesignation() == null || staff.getDesignation().isEmpty()) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Staff Designation is required");
        }

        if (staff.getContact() == null || !isValid(staff.getContact(), Regex.getMobilePattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Invalid Staff Contact");
        }

        if (staff.getEmail() == null || !isValid(staff.getEmail(), Regex.getEmailPattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Invalid Staff Email");
        }

        if (staff.getFields() == null || staff.getFields().isEmpty()) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Staff must be assigned to at least one Field");
        }

        return null;
    }

    public static CustomStatus validateVehicleDTO(VehicleDTO vehicle) {
        if (vehicle == null) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Vehicle details are required");
        }

        if (vehicle.getCategory() == null || vehicle.getCategory().isEmpty()) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Vehicle category is required");
        }

        if (vehicle.getFuelType() == null || vehicle.getFuelType().isEmpty()) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Fuel type is required");
        }

        if (vehicle.getStatus() == null || vehicle.getStatus().isEmpty()) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Vehicle status is required");
        }

        if (vehicle.getRemarks() != null && vehicle.getRemarks().length() > 255) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Remarks are too long");
        }

        CustomStatus staffValidation = validateStaffDTO(vehicle.getStaff());
        if (staffValidation != null) {
            return staffValidation;
        }

        return null;
    }

    public static CustomStatus validateLogDTO(LogDTO logDTO){
        if (!isValid(logDTO.getObservation(), Regex.getAddressPattern())) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Invalid log observation");
        }
        if (logDTO.getDate() == null) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "log date is required");
        }
        if (logDTO.getObservationImage() == null) {
            return new ErrorStatus(HttpStatus.BAD_REQUEST.value(), "Log image is required");
        }

        return null;
    }
}

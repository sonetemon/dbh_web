package com.dbh.ilps_service.filter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterCriteria {
    private String key;           // The field name to filter
    private String operation;     // The operation (e.g., =, >, <, LIKE)
    private Object value;         // The value to compare
    private String dataType;      // Optional: Data type for dynamic casting

    public FilterCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public FilterCriteria(String key, String operation, Object value, String dataType) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.dataType = dataType;
    }

    // Getters and setters
}


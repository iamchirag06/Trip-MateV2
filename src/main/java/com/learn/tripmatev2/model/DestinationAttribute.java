package com.learn.tripmatev2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "destination_attributes", indexes = {
    @Index(name = "idx_attr_dest", columnList = "destination_id"),
    @Index(name = "idx_attr_type", columnList = "attributeType")
})
public class DestinationAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Attribute type is required")
    @Size(max = 100)
    private String attributeType; // e.g., Language, Religion, Culture, Transportation

    @NotBlank(message = "Attribute value is required")
    @Size(max = 200)
    private String attributeValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestinationAttribute that = (DestinationAttribute) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DestinationAttribute{" +
                "id=" + id +
                ", attributeType='" + attributeType + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}

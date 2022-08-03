package com.example.copmprob.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "activate_from", nullable = false, columnDefinition = "DATE")
    private LocalDate dateFrom = LocalDate.now();

    @Column(name = "activate_to", nullable = false, columnDefinition = "DATE")
    private LocalDate  dateTo = LocalDate.now().plusDays(30);

    @ManyToOne
    private Category category;

    @ManyToOne
    private Users author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }
}

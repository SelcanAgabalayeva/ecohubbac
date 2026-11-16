package com.ecohub.ecohubbac.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Azərbaycan dili
    @Column(name = "title_az", nullable = false)
    private String titleAz;

    @Column(name = "subtitle_az")
    private String subtitleAz;

    @Column(name = "description_az", columnDefinition = "TEXT")
    private String descriptionAz;

    @Column(name = "content_az", columnDefinition = "TEXT")
    private String contentAz;
    // İngilis dili
    @Column(name = "title_en")
    private String titleEn;

    @Column(name = "subtitle_en")
    private String subtitleEn;

    @Column(name = "description_en", columnDefinition = "TEXT")
    private String descriptionEn;

    @Column(name = "content_en", columnDefinition = "TEXT")
    private String contentEn;
    @Column(name = "news_date")
    private String date;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private NewsCategory category;
}

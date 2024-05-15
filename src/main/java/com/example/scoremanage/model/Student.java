package com.example.scoremanage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "STUDENT", uniqueConstraints = {@UniqueConstraint(columnNames = "NO")})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NO", length = 10, nullable = false)
    private String no;

    @Column(name = "NAME", length = 10, nullable = true)
    private String name;

    @Column(name="ENT_YEAR", length = 10, nullable = true)
    @NotNull(message = "入学年度を選択してください。")
    private Integer entYear;

    @Column(name = "CLASS_NUM", length = 3, nullable = true)
    private String classNum;

    @Column(name = "IS_ATTEND", nullable = true)
    private Boolean isAttend;

    @Column(name = "SCHOOL_CD", length = 3, nullable = true)
    private String schoolCd;
}
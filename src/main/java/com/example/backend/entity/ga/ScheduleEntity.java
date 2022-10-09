package com.example.backend.entity.ga;

import com.example.backend.entity.Class;
import lombok.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int deleted;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.EAGER)
    private List<Class> classes;
    
}

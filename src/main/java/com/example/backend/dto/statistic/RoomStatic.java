package com.example.backend.dto.statistic;

import com.example.backend.entity.Room;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomStatic {

    private Long count;

    private Room room;

}

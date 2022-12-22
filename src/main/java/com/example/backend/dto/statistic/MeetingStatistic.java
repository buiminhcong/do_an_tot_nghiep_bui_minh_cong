package com.example.backend.dto.statistic;

import com.example.backend.entity.MeetingTime;
import com.example.backend.entity.Room;
import lombok.*;

import java.io.Serializable;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingStatistic {

    private Long count;

    private MeetingTime meetingTime;
}

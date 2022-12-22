package com.example.backend.entity.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Population {

    private List<Schedule> schedules;

    public Population(int size, Data data) {  // ==> Bước 1: khởi tạo quần thể số lượng tkb và dữ liệu
        schedules = new ArrayList<Schedule>(size);
        // Tạo quần thể schedule
        // Thêm các scedule vào quần thể
        IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(data).initialize()));
    }

    public List<Schedule> getSchedules() { // lấy ra danh sách quần thể
        return schedules;
    }

    public Population sortByFitness() {
        schedules.sort((schedules1, schedules2) -> {
            int returnValue = 0;
            if (schedules1.getFitness() > schedules2.getFitness()) returnValue = -1;
            else if (schedules1.getFitness() < schedules2.getFitness()) returnValue = 1;
            return returnValue;
        });
        return this;
    }

}

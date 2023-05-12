package kz.itstep.model;

import kz.itstep.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table("booking")
public class Booking {
    private Long id;
    private Customer customer;
    private Room room;
    private Date start;
    private Date end;
}

package com.tamdao.api1.Controller;

import com.tamdao.api1.dto.CreateStaffRequest;
import com.tamdao.api1.entity.Staff;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/staffs")
public class StaffController {
    private static List<Staff> staffs = new LinkedList<>();
    // 1. Tao staff
    // Method: Post
    // Path: hotels
    @PostMapping
    public Staff createStaff(@RequestBody CreateStaffRequest request) {
        Staff staff = new Staff();
        staff.setStaffId(request.getId());
        staff.setName(request.getName());
        staff.setAge(request.getAge());
        staff.setEmail(request.getEmail());
        staffs.add(staff);
        return staff;
    }

    // 2. lay dan sach staff
    // Method: Get
    //Path: /api/v1/hotels
    @GetMapping
    public List<Staff> getStaffs() {
        return staffs;
    }

    // 3.lay thong tin cua  1 staff
    // Method: Get
    // Path /api/v1/hotel/<hotel_id>
    @GetMapping("/{staffId}")
    public Staff getStaff(@PathVariable String staffId) {
        return findStaffById(staffId);
    }

    // 4. Cap nhat room
    // Method: PUT
    //Path: /api/v1/room/<room-id>

    @PutMapping("/{staffId}")
    public Staff updateStaff(@PathVariable String staffId,
                             @RequestBody CreateStaffRequest request) {
        Staff staff = findStaffById(staffId);
        staff.setName(request.getName());
        staff.setAge(request.getAge());
        staff.setEmail(request.getEmail());

        return staff;
    }

    //5 Vo hieu hoa 1 nhan vien
    // Method: Delete
    //Path: /api/v1/hotel/<room_id>
    @DeleteMapping("/{staffId}")
    public String deleteStaff(@PathVariable String staffId) {
        Staff staff = findStaffById(staffId);
        if (staff == null) {
            return "Khong tim thay";
        }
        staffs.remove(staff);
        return "Xoa thanh cong";
    }


    public Staff findStaffById(String id) {
        for (Staff staff : staffs) {
            if (staff.getStaffId().equals(id)) {
                return staff;
            }
        }

        return null;
    }


}

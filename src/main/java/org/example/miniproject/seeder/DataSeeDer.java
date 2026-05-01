package org.example.miniproject.seeder;

import lombok.RequiredArgsConstructor;
import org.example.miniproject.model.Department;
import org.example.miniproject.model.Employee;
import org.example.miniproject.repository.DepartmentRepository;
import org.example.miniproject.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeDer implements CommandLineRunner {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (employeeRepository.count() == 0){
            Department it = new Department(null, "it", "Hanoi", null);
            Department  marketing = new Department(null, "Marketing", "Ho Chi Minh", null);

            departmentRepository.save(it);
            departmentRepository.save(marketing);

            Employee e1 = new Employee(null, "An", 25, "https://cdn-media.sforum.vn/storage/app/media/thanhhuyen/%E1%BA%A3nh%20%C4%91%E1%BA%B9p%20v%C5%A9ng%20t%C3%A0u/1/anh-dep-vung-tau-1.jpg", true, it);
            Employee e2 = new Employee(null, "Bình", 30, "https://cdn-media.sforum.vn/storage/app/media/thanhhuyen/%E1%BA%A3nh%20%C4%91%E1%BA%B9p%20v%C5%A9ng%20t%C3%A0u/1/anh-dep-vung-tau-1.jpg", true,marketing);
            Employee e3 = new Employee(null, "Cường", 28, "https://cdn-media.sforum.vn/storage/app/media/thanhhuyen/%E1%BA%A3nh%20%C4%91%E1%BA%B9p%20v%C5%A9ng%20t%C3%A0u/1/anh-dep-vung-tau-1.jpg", false, it);

            employeeRepository.save(e1);
            employeeRepository.save(e2);
            employeeRepository.save(e3);
        }
    }
}

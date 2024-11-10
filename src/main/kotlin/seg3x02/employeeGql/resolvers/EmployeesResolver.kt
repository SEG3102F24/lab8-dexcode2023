package seg3x02.employeeGql.resolvers

import org.springframework.stereotype.Controller

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.coxautodev.graphql.tools.GraphQLMutationResolver
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput

@Controller
class EmployeesResolver(
    private val employeesRepository: EmployeesRepository
) : GraphQLQueryResolver, GraphQLMutationResolver {

    fun employees(): List<Employee> {
        return employeesRepository.findAll()
    }

    fun employee(id: String): Employee? {
        return employeesRepository.findById(id).orElse(null)
    }

    fun createEmployee(input: CreateEmployeeInput): Employee {
        val employee = Employee(
            name = input.name,
            dateOfBirth = input.dateOfBirth,
            city = input.city,
            salary = input.salary,
            gender = input.gender,
            email = input.email
        )
        return employeesRepository.save(employee)
    }
}
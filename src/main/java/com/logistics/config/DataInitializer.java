package com.logistics.config;

import com.logistics.entity.*;
import com.logistics.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final SupplierRepository supplierRepo;
    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;
    private final InventoryRepository inventoryRepo;
    private final CustomerRepository customerRepo;
    private final CustomerOrderRepository orderRepo;
    private final ShipmentRepository shipmentRepo;
    private final VehicleRepository vehicleRepo;
    private final RouteRepository routeRepo;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner initData() {
        return args -> {
            if (roleRepo.count() > 0) return;
            log.info("Initializing demo data...");

            // Roles - Fixed using Builder pattern matching your entity configurations
            Role adminRole = roleRepo.save(Role.builder().name("ROLE_ADMIN").description("System Administrator").build());
            Role managerRole = roleRepo.save(Role.builder().name("ROLE_MANAGER").description("Manager").build());
            Role whRole = roleRepo.save(Role.builder().name("ROLE_WAREHOUSE_MANAGER").description("Warehouse Manager").build());
            Role tmRole = roleRepo.save(Role.builder().name("ROLE_TRANSPORT_MANAGER").description("Transport Manager").build());
            Role empRole = roleRepo.save(Role.builder().name("ROLE_EMPLOYEE").description("Employee").build());

            // Users
            User admin = User.builder()
                    .username("admin").email("admin@smartlogistics.com")
                    .password(passwordEncoder.encode("admin123"))
                    .firstName("System").lastName("Admin")
                    .department("IT").enabled(true).accountNonLocked(true)
                    .roles(Set.of(adminRole, managerRole)).build();
            userRepo.save(admin);

            User manager = User.builder()
                    .username("manager").email("manager@smartlogistics.com")
                    .password(passwordEncoder.encode("manager123"))
                    .firstName("John").lastName("Manager")
                    .department("Operations").enabled(true).accountNonLocked(true)
                    .roles(Set.of(managerRole)).build();
            userRepo.save(manager);

            User employee = User.builder()
                    .username("employee").email("employee@smartlogistics.com")
                    .password(passwordEncoder.encode("emp123"))
                    .firstName("Jane").lastName("Employee")
                    .department("Warehouse").enabled(true).accountNonLocked(true)
                    .roles(Set.of(empRole)).build();
            userRepo.save(employee);

            // Categories
            Category electronics = categoryRepo.save(Category.builder()
                    .name("Electronics").code("ELEC").description("Electronic components and devices")
                    .active(true).build());
            Category machinery = categoryRepo.save(Category.builder()
                    .name("Machinery").code("MACH").description("Industrial machinery and equipment")
                    .active(true).build());
            Category consumables = categoryRepo.save(Category.builder()
                    .name("Consumables").code("CONS").description("Consumable goods")
                    .active(true).build());
            Category rawMaterials = categoryRepo.save(Category.builder()
                    .name("Raw Materials").code("RAW").description("Raw production materials")
                    .active(true).build());

            // Suppliers
            Supplier techCorp = supplierRepo.save(Supplier.builder()
                    .name("TechCorp Solutions").code("TC001").email("info@techcorp.com")
                    .phone("+1-555-0100").address("123 Tech Street").city("San Francisco")
                    .country("USA").contactPerson("Alice Johnson").status(Supplier.Status.ACTIVE).build());
            Supplier globalParts = supplierRepo.save(Supplier.builder()
                    .name("Global Parts Inc").code("GP002").email("contact@globalparts.com")
                    .phone("+1-555-0200").address("456 Industrial Ave").city("Chicago")
                    .country("USA").contactPerson("Bob Smith").status(Supplier.Status.ACTIVE).build());
            Supplier asiaTrade = supplierRepo.save(Supplier.builder()
                    .name("Asia Trade Hub").code("AT003").email("trade@asiatrade.com")
                    .phone("+86-10-5555-0300").address("789 Commerce Rd").city("Shanghai")
                    .country("China").contactPerson("Chen Wei").status(Supplier.Status.ACTIVE).build());

            // Products
            Product p1 = productRepo.save(Product.builder()
                    .name("Industrial Controller Unit").sku("ICU-001")
                    .description("Advanced PLC controller for industrial automation").
                    unitPrice(new BigDecimal("1299.99")).costPrice(new BigDecimal("850.00"))
                    .unit("PCS").brand("Siemens").reorderPoint(5).reorderQuantity(20)
                    .status(Product.Status.ACTIVE).category(electronics).supplier(techCorp).build());
            Product p2 = productRepo.save(Product.builder()
                    .name("Hydraulic Pump Assembly").sku("HPA-002")
                    .description("Heavy-duty hydraulic pump for industrial use")
                    .unitPrice(new BigDecimal("2499.00")).costPrice(new BigDecimal("1600.00"))
                    .unit("PCS").brand("Bosch").reorderPoint(3).reorderQuantity(10)
                    .status(Product.Status.ACTIVE).category(machinery).supplier(globalParts).build());
            Product p3 = productRepo.save(Product.builder()
                    .name("Stainless Steel Bolts M12").sku("SSB-003")
                    .description("M12 stainless steel hex bolts, box of 100")
                    .unitPrice(new BigDecimal("45.99")).costPrice(new BigDecimal("22.00"))
                    .unit("BOX").brand("FastenerPro").reorderPoint(50).reorderQuantity(200)
                    .status(Product.Status.ACTIVE).category(consumables).supplier(asiaTrade).build());
            Product p4 = productRepo.save(Product.builder()
                    .name("Copper Wire 2.5mm").sku("CW-004")
                    .description("2.5mm copper wire, 100m roll")
                    .unitPrice(new BigDecimal("189.99")).costPrice(new BigDecimal("110.00"))
                    .unit("ROLL").brand("CopperTech").reorderPoint(20).reorderQuantity(100)
                    .status(Product.Status.ACTIVE).category(rawMaterials).supplier(globalParts).build());
            Product p5 = productRepo.save(Product.builder()
                    .name("Safety Helmet Type-E").sku("SH-005")
                    .description("Industrial safety helmet, CE certified")
                    .unitPrice(new BigDecimal("34.99")).costPrice(new BigDecimal("15.00"))
                    .unit("PCS").brand("SafeGuard").reorderPoint(30).reorderQuantity(100)
                    .status(Product.Status.ACTIVE).category(consumables).supplier(techCorp).build());

         // Warehouses - Updated to pass BigDecimal values
            Warehouse wh1 = warehouseRepo.save(Warehouse.builder()
                    .name("Main Distribution Center").code("WH-001")
                    .address("1000 Logistics Blvd").city("Los Angeles").state("CA")
                    .country("USA").zipCode("90001")
                    .managerName("Robert Chen").phone("+1-555-1001")
                    .totalCapacity(new BigDecimal("50000.0")).usedCapacity(new BigDecimal("32000.0")).capacityUnit("m²")
                    .status(Warehouse.Status.ACTIVE).build());
            Warehouse wh2 = warehouseRepo.save(Warehouse.builder()
                    .name("East Coast Hub").code("WH-002")
                    .address("2000 Harbor Drive").city("New York").state("NY")
                    .country("USA").zipCode("10001")
                    .managerName("Sarah Miller").phone("+1-555-1002")
                    .totalCapacity(new BigDecimal("35000.0")).usedCapacity(new BigDecimal("18000.0")).capacityUnit("m²")
                    .status(Warehouse.Status.ACTIVE).build());
            Warehouse wh3 = warehouseRepo.save(Warehouse.builder()
                    .name("Midwest Storage Facility").code("WH-003")
                    .address("3000 Central Ave").city("Chicago").state("IL")
                    .country("USA").zipCode("60601")
                    .managerName("Mike Davis").phone("+1-555-1003")
                    .totalCapacity(new BigDecimal("28000.0")).usedCapacity(new BigDecimal("9800.0")).capacityUnit("m²")
                    .status(Warehouse.Status.ACTIVE).build());

            // Inventory
            inventoryRepo.save(Inventory.builder().product(p1).warehouse(wh1)
                    .quantityOnHand(120).quantityReserved(15).binLocation("A1-01")
                    .averageCost(new BigDecimal("850.00")).build());
            inventoryRepo.save(Inventory.builder().product(p2).warehouse(wh1)
                    .quantityOnHand(45).quantityReserved(5).binLocation("B2-03")
                    .averageCost(new BigDecimal("1600.00")).build());
            inventoryRepo.save(Inventory.builder().product(p3).warehouse(wh2)
                    .quantityOnHand(8).quantityReserved(0).binLocation("C3-05")
                    .averageCost(new BigDecimal("22.00")).build());
            inventoryRepo.save(Inventory.builder().product(p4).warehouse(wh2)
                    .quantityOnHand(350).quantityReserved(50).binLocation("D4-02")
                    .averageCost(new BigDecimal("110.00")).build());
            inventoryRepo.save(Inventory.builder().product(p5).warehouse(wh3)
                    .quantityOnHand(25).quantityReserved(0).binLocation("E5-07")
                    .averageCost(new BigDecimal("15.00")).build());
            inventoryRepo.save(Inventory.builder().product(p1).warehouse(wh2)
                    .quantityOnHand(4).quantityReserved(0).binLocation("A1-11")
                    .averageCost(new BigDecimal("850.00")).build());

            // Customers
            Customer c1 = customerRepo.save(Customer.builder()
                    .name("Acme Manufacturing Corp").code("CUS-001")
                    .email("procurement@acme.com").phone("+1-555-2001")
                    .billingAddress("100 Factory Lane, Detroit, MI 48201")
                    .shippingAddress("100 Factory Lane, Detroit, MI 48201")
                    .city("Detroit").state("MI").country("USA").contactPerson("Tom Wilson")
                    .customerType(Customer.CustomerType.CORPORATE)
                    .creditLimit(new BigDecimal("500000.00")).status(Customer.Status.ACTIVE).build());
            Customer c2 = customerRepo.save(Customer.builder()
                    .name("BuildRight Construction").code("CUS-002")
                    .email("supply@buildright.com").phone("+1-555-2002")
                    .billingAddress("200 Builder Ave, Houston, TX 77001")
                    .city("Houston").state("TX").country("USA").contactPerson("Lisa Brown")
                    .customerType(Customer.CustomerType.WHOLESALE)
                    .creditLimit(new BigDecimal("250000.00")).status(Customer.Status.ACTIVE).build());
            Customer c3 = customerRepo.save(Customer.builder()
                    .name("TechStart Systems").code("CUS-003")
                    .email("ops@techstart.io").phone("+1-555-2003")
                    .billingAddress("300 Innovation Park, Austin, TX 78701")
                    .city("Austin").state("TX").country("USA").contactPerson("James Lee")
                    .customerType(Customer.CustomerType.CORPORATE)
                    .creditLimit(new BigDecimal("150000.00")).status(Customer.Status.ACTIVE).build());

            // Routes
            Route r1 = routeRepo.save(Route.builder().name("LA to NYC Express").code("RT-001")
                    .origin("Los Angeles, CA").destination("New York, NY")
                    .distanceKm(new BigDecimal("4484")).estimatedHours(new BigDecimal("48.0"))
                    .status(Route.Status.ACTIVE).build());
            Route r2 = routeRepo.save(Route.builder().name("LA to Chicago Midwest").code("RT-002")
                    .origin("Los Angeles, CA").destination("Chicago, IL")
                    .distanceKm(new BigDecimal("3245")).estimatedHours(new BigDecimal("36.0"))
                    .status(Route.Status.ACTIVE).build());
            Route r3 = routeRepo.save(Route.builder().name("NYC to Chicago").code("RT-003")
                    .origin("New York, NY").destination("Chicago, IL")
                    .distanceKm(new BigDecimal("1272")).estimatedHours(new BigDecimal("15.0"))
                    .status(Route.Status.ACTIVE).build());

            // Vehicles
            Vehicle v1 = vehicleRepo.save(Vehicle.builder()
                    .licensePlate("CA-TRK-001").make("Freightliner").model("Cascadia")
                    .year(2022).type(Vehicle.VehicleType.TRUCK)
                    .maxLoadCapacity(new BigDecimal("22000")).capacityUnit("kg")
                    .driverName("Carlos Rodriguez").driverPhone("+1-555-3001")
                    .status(Vehicle.VehicleStatus.AVAILABLE)
                    .insuranceExpiry(LocalDate.now().plusYears(1))
                    .nextMaintenanceDate(LocalDate.now().plusMonths(3)).build());
            Vehicle v2 = vehicleRepo.save(Vehicle.builder()
                    .licensePlate("NY-VAN-002").make("Mercedes-Benz").model("Sprinter")
                    .year(2021).type(Vehicle.VehicleType.VAN)
                    .maxLoadCapacity(new BigDecimal("3500")).capacityUnit("kg")
                    .driverName("David Park").driverPhone("+1-555-3002")
                    .status(Vehicle.VehicleStatus.ON_ROUTE)
                    .insuranceExpiry(LocalDate.now().plusYears(1))
                    .nextMaintenanceDate(LocalDate.now().plusMonths(2)).build());
            Vehicle v3 = vehicleRepo.save(Vehicle.builder()
                    .licensePlate("IL-TRK-003").make("Volvo").model("VNL 760")
                    .year(2023).type(Vehicle.VehicleType.TRUCK)
                    .maxLoadCapacity(new BigDecimal("18000")).capacityUnit("kg")
                    .driverName("Maria Santos").driverPhone("+1-555-3003")
                    .status(Vehicle.VehicleStatus.AVAILABLE)
                    .insuranceExpiry(LocalDate.now().plusYears(2))
                    .nextMaintenanceDate(LocalDate.now().plusMonths(6)).build());

            // Orders
            CustomerOrder order1 = CustomerOrder.builder()
                    .orderNumber("ORD-20241201-0001").customer(c1).warehouse(wh1)
                    .status(CustomerOrder.OrderStatus.PROCESSING)
                    .paymentStatus(CustomerOrder.PaymentStatus.PAID)
                    .subtotal(new BigDecimal("25999.90")).taxAmount(new BigDecimal("2600.00"))
                    .shippingCost(new BigDecimal("500.00")).discountAmount(new BigDecimal("1000.00"))
                    .totalAmount(new BigDecimal("28099.90"))
                    .shippingAddress("100 Factory Lane, Detroit, MI 48201")
                    .shippingMethod("Express Freight")
                    .expectedDeliveryDate(LocalDate.now().plusDays(5))
                    .createdBy("admin").build();
            orderRepo.save(order1);

            CustomerOrder order2 = CustomerOrder.builder()
                    .orderNumber("ORD-20241201-0002").customer(c2).warehouse(wh2)
                    .status(CustomerOrder.OrderStatus.PENDING)
                    .paymentStatus(CustomerOrder.PaymentStatus.PENDING)
                    .subtotal(new BigDecimal("12450.00")).taxAmount(new BigDecimal("1245.00"))
                    .shippingCost(new BigDecimal("350.00")).discountAmount(BigDecimal.ZERO)
                    .totalAmount(new BigDecimal("14045.00"))
                    .shippingAddress("200 Builder Ave, Houston, TX 77001")
                    .shippingMethod("Standard Freight")
                    .expectedDeliveryDate(LocalDate.now().plusDays(10))
                    .createdBy("manager").build();
            orderRepo.save(order2);

            CustomerOrder order3 = CustomerOrder.builder()
                    .orderNumber("ORD-20241201-0003").customer(c3).warehouse(wh1)
                    .status(CustomerOrder.OrderStatus.SHIPPED)
                    .paymentStatus(CustomerOrder.PaymentStatus.PAID)
                    .subtotal(new BigDecimal("8750.00")).taxAmount(new BigDecimal("875.00"))
                    .shippingCost(new BigDecimal("200.00")).discountAmount(new BigDecimal("500.00"))
                    .totalAmount(new BigDecimal("9325.00"))
                    .shippingAddress("300 Innovation Park, Austin, TX 78701")
                    .shippingMethod("Standard Ground")
                    .expectedDeliveryDate(LocalDate.now().plusDays(3))
                    .createdBy("admin").build();
            orderRepo.save(order3);

            // Shipments
            Shipment s1 = Shipment.builder()
                    .trackingNumber("SHP-202412010001").order(order1).vehicle(v1).route(r2)
                    .status(Shipment.ShipmentStatus.IN_TRANSIT)
                    .originAddress("1000 Logistics Blvd, Los Angeles, CA")
                    .destinationAddress("100 Factory Lane, Detroit, MI")
                    .scheduledDate(LocalDate.now().minusDays(1))
                    .estimatedDelivery(LocalDate.now().plusDays(4))
                    .weight(new BigDecimal("850.0")).shippingCost(new BigDecimal("500.00"))
                    .driverName("Carlos Rodriguez").driverPhone("+1-555-3001").build();
            shipmentRepo.save(s1);

            Shipment s2 = Shipment.builder()
                    .trackingNumber("SHP-202412010002").order(order3).vehicle(v2).route(r1)
                    .status(Shipment.ShipmentStatus.OUT_FOR_DELIVERY)
                    .originAddress("2000 Harbor Drive, New York, NY")
                    .destinationAddress("300 Innovation Park, Austin, TX")
                    .scheduledDate(LocalDate.now().minusDays(3))
                    .estimatedDelivery(LocalDate.now().plusDays(1))
                    .weight(new BigDecimal("320.5")).shippingCost(new BigDecimal("200.00"))
                    .driverName("David Park").driverPhone("+1-555-3002").build();
            shipmentRepo.save(s2);

            log.info("Demo data initialized successfully.");
        };
    }
}
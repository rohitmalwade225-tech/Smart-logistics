package com.logistics.controller;

import com.logistics.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("stats", dashboardService.getStats());
        model.addAttribute("recentOrders", dashboardService.getRecentOrders());
        model.addAttribute("recentShipments", dashboardService.getRecentShipments());
        model.addAttribute("lowStockProducts", dashboardService.getLowStockProducts());
        model.addAttribute("pageTitle", "Dashboard");
        model.addAttribute("activePage", "dashboard");
        return "dashboard/dashboard";
    }
}

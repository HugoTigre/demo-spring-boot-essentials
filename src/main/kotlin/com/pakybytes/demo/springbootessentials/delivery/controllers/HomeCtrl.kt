package com.pakybytes.demo.springbootessentials.delivery.controllers

import com.pakybytes.demo.springbootessentials.conf.SpringBootEssentialsConfig
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class HomeCtrl(private val conf: SpringBootEssentialsConfig) {

    @GetMapping("/")
    fun index(model: Model): String {
        model["title"] = conf.app.name
        model["environment"] = conf.env
        model["app"] = conf.app.name
        model["version"] = conf.app.version
        return "home"
    }

}
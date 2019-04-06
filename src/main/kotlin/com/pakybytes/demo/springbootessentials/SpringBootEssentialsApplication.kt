package com.pakybytes.demo.springbootessentials

import com.pakybytes.demo.springbootessentials.conf.SpringBootEssentialsConfig
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SpringBootEssentialsConfig::class)
class SpringBootEssentialsApplication

fun main(args: Array<String>) {
	runApplication<SpringBootEssentialsApplication>(*args){
		setBannerMode(Banner.Mode.OFF)
	}
}

package dev.playground.hyun.controller

import dev.playground.hyun.common.ApiResponse
import dev.playground.hyun.domain.playground.PlaygroundService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaygroundController(
    private val playgroundService: PlaygroundService,
) {

    @GetMapping(value = ["/v1/test"])
    fun test(): ApiResponse<Unit> {
        playgroundService.test()
        return ApiResponse.success()
    }
}

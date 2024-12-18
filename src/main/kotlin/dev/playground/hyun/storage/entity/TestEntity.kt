package dev.playground.hyun.storage.entity

import jakarta.persistence.*

@Entity
@Table(name = "test")
class TestEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name")
    val name: String?,
)
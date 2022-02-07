package com.cogni.demo

interface Repository<T, K> {
    fun readAll(): List<T>?
    fun read(id: K): T
    fun read(filter: () -> Unit): T
    fun create(entity: T): T
    fun update(entity: T): T
    fun delete(entity: T): T
}
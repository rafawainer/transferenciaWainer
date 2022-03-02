package br.com.itau.bootcamp.repository

import br.com.itau.bootcamp.event.Event
import br.com.itau.bootcamp.event.KeyEvent
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface EventRepository: CrudRepository<Event, KeyEvent> {

    @EnableScan
    fun findAllByPk(cpfOrigem: String): List<Event>
    fun findAllByPkAndSkStartsWith(cpfOrigem: String, id:String): Event

}
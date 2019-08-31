package algorithms.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class IntervalSchedulingTest {

    @Test
    fun shouldMaximizeSessionCountUsingMultipleRooms() {
        val sessions = createSessions()
        val roomCount = 4
        val scheduling = IntervalScheduling(sessions, roomCount)
        val rooms = scheduling.getRooms()

        assertEquals(roomCount, rooms.size)
        assertEquals(10, totalSessionCount(rooms))
        rooms.forEach { assertValidSessionInRoom(it) }
    }

    @Test
    fun shouldMaximizeSessionCountUsingSingleRoom(){
        val sessions = createSessions()
        val roomCount = 1
        val scheduling = IntervalScheduling(sessions, roomCount)
        val rooms = scheduling.getRooms()

        assertEquals(roomCount, rooms.size)
        assertEquals(4, totalSessionCount(rooms))
        rooms.forEach { assertValidSessionInRoom(it) }
    }

    private fun totalSessionCount(rooms: Set<Room>): Int {
        return rooms.asSequence()
                .map { it.getSessions().size }
                .sum()
    }

    private fun assertValidSessionInRoom(room: Room) {
        var prevEndTime = 0
        for (session in room.getSessions()) {
            assertTrue(session.start <= session.end)
            assertTrue(prevEndTime < session.start)
            prevEndTime = session.end
        }
    }

    private fun createSessions(): List<Session> {
        return listOf(
                Session(1, 4), Session(5, 5),
                Session(1, 6), Session(7, 8),
                Session(2, 5), Session(2, 9),
                Session(10, 22), Session(11, 14),
                Session(7, 12), Session(10, 15)
        )
    }

}
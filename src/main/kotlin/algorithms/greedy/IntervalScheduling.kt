package algorithms.greedy

import java.util.*
import java.util.Collections.sort

/**
 * Implementation of [Interval Scheduling](https://en.wikipedia.org/wiki/Interval_scheduling).
 * Current implementation finds the maximum of sessions which can be held in multiple rooms.
 *
 * @param sessions List of sessions to be distributed to multiple rooms
 * @param roomCount Number of rooms available
 */
class IntervalScheduling(private val sessions: List<Session>, private val roomCount: Int) {

    private val rooms = TreeSet<Room>()
    private var counter = 0

    init {
        sort(sessions)
        for (i in 1..roomCount) {
            rooms.add(Room(counter++, 0))
        }
        setSchedule()
    }

    /**
     * Get the set of rooms.
     *
     * @return the set of rooms
     */
    fun getRooms() = rooms

    /**
     * Performs the distribution of session to the rooms.
     */
    private fun setSchedule() {
        for (session in sessions) {
            val limit = Room(counter++, session.start)
            val room = rooms.floor(limit)
            if (room != null) {
                rooms.remove(room)
                room.addSession(session)
                rooms.add(room)
            }
        }
    }
}

/**
 * Representing a room where a session or work unit may be carried out.
 *
 * @param id Id of the room
 * @param avail The next available time for a session to be carried out in the room
 */
class Room(private val id: Int, private val avail: Int) : Comparable<Room> {

    private val sessions = mutableListOf<Session>()
    private var nextAvailable: Int

    init {
        nextAvailable = avail
    }

    /**
     * Get the sessions assigned to this room.
     *
     * @return list of sessions assigned to this room
     */
    fun getSessions() = sessions

    /**
     * Get the number of sessions assigned this room.
     *
     * @return number of session assigned to this room
     */
    fun sessionCount() = sessions.size

    /**
     * Add a session to this room.
     *
     * @param session Session to be added to the room
     */
    fun addSession(session: Session) {
        sessions.add(session)
        nextAvailable = session.end + 1
    }

    override fun compareTo(other: Room): Int {
        return when (nextAvailable) {
            other.nextAvailable -> id - other.id
            else -> nextAvailable - other.nextAvailable
        }
    }
}

/**
 * Representing a session or work unit, starting and ending at a specified time.
 *
 * @param start Start time of the session
 * @param end End time of the session
 */
class Session(val start: Int, val end: Int) : Comparable<Session> {

    override fun compareTo(other: Session): Int {
        return when (end) {
            other.end -> start - other.start
            else -> end - other.end
        }
    }
}
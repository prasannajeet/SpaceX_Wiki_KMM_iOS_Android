package app.prasanpani.dogsapi.utils.datetime

import kotlinx.datetime.*
import kotlinx.serialization.Serializable
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * A [DateTime] object is a representation of date and time in the application
 * created out of epoch milliseconds
 * @author Prasan Pani
 * @constructor Pass raw date time as seconds from epoch
 * @property rawDateTime Raw value of date time as milis from epoch
 */
@Serializable(with = DateTimeSerializer::class)
data class DateTime constructor(private val rawDateTime: Long = Clock.System.now().toEpochMilliseconds()) {

    private val isoDateTime: Instant = Instant.fromEpochMilliseconds(rawDateTime)

    companion object {
        fun now(): DateTime = DateTime()
    }

    infix fun secondsAwayFrom(to: DateTime): Long =
        (to.isoDateTime - this.isoDateTime).inWholeSeconds

    //fun getFormattedUniversalDateTime() = Instant.parse()

    fun isToday(): Boolean =
        this.isoDateTime.toLocalDateTime(TimeZone.currentSystemDefault()).dayOfMonth == Clock.System.now()
            .toLocalDateTime(
                TimeZone.currentSystemDefault()
            ).dayOfMonth
                &&
                this.isoDateTime.toLocalDateTime(TimeZone.currentSystemDefault()).monthNumber == Clock.System.now()
            .toLocalDateTime(
                TimeZone.currentSystemDefault()
            ).monthNumber
                &&
                this.isoDateTime.toLocalDateTime(TimeZone.currentSystemDefault()).year == Clock.System.now()
            .toLocalDateTime(
                TimeZone.currentSystemDefault()
            ).year

    fun getMonthFromDate(): String = isoDateTime.toLocalDateTime(TimeZone.currentSystemDefault()).month.name

    override fun toString(): String {
        return isoDateTime.toString()
    }

    infix fun plusSeconds(seconds: Long): DateTime =
        DateTime(isoDateTime.plus(seconds.seconds).toEpochMilliseconds())

    infix fun minusSeconds(seconds: Long): DateTime =
        DateTime(isoDateTime.minus(seconds.seconds).toEpochMilliseconds())

    infix fun plusMinutes(minutes: Int): DateTime =
        DateTime(isoDateTime.plus(minutes.minutes).toEpochMilliseconds())

    infix fun minusMinutes(minutes: Int): DateTime =
        DateTime(isoDateTime.minus(minutes.minutes).toEpochMilliseconds())

    infix fun plusHours(hours: Int): DateTime =
        DateTime(isoDateTime.plus(hours.hours).toEpochMilliseconds())

    infix fun minusHours(hours: Int): DateTime =
        DateTime(isoDateTime.minus(hours.hours).toEpochMilliseconds())

    infix fun plusDays(days: Int): DateTime =
        DateTime(isoDateTime.plus(days.days).toEpochMilliseconds())

    infix fun minusDays(days: Int): DateTime =
        DateTime(isoDateTime.minus(days.days).toEpochMilliseconds())

    infix fun isBefore(dateTime: DateTime): Boolean =
        isoDateTime.until(Clock.System.now(), DateTimeUnit.MILLISECOND) > 0

    infix fun durationAsString(dateTime: DateTime): String =
        isoDateTime.periodUntil(this.isoDateTime, TimeZone.currentSystemDefault()).toString()
/*
    enum class DateTimeDisplayFormat(val format: Instant) {

    }*/
}
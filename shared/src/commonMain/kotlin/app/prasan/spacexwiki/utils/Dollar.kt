package app.prasan.spacexwiki.utils

import kotlinx.serialization.Serializable

@Serializable(with = DollarSerializer::class)
data class Dollar(val rawAmount: Long) {
    private fun getFormattedAmount() = "$%.2f"

    companion object {
        val ZERO = Dollar(0L)

        fun parseStringToDollar(candidate: String): Dollar {
            return if (candidate.startsWith("$")) {
                try {
                    Dollar(candidate.subSequence(1 until candidate.length).toString().toLong())
                } catch (e: Exception) {
                    ZERO
                }
            } else ZERO
        }
    }

    infix fun isSameDollarAs(otherAmount: Dollar) = this.rawAmount == otherAmount.rawAmount

    override fun hashCode(): Int {
        return rawAmount.hashCode()
    }

    override fun toString(): String {
        return getFormattedAmount()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Dollar) {
            this isSameDollarAs other
        } else {
            super.equals(other)
        }
    }

    fun compareTo(amount: Dollar) = this.rawAmount.compareTo(amount.rawAmount)
}
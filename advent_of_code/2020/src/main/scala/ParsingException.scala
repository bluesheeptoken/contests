final case class ParsingException(
    private val message: String = "Cannot parse line",
    private val cause: Throwable = None.orNull
) extends Exception(message, cause)

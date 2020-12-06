final case class SolutionNotFoundException(
    private val message: String = "Solution not found exception",
    private val cause: Throwable = None.orNull
) extends Exception(message, cause)

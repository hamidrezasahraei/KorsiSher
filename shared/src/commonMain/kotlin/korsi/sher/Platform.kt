package korsi.sher

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
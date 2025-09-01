package sourabh.pal.mysocial.common.data.api.interceptors

import io.ktor.client.plugins.api.createClientPlugin
import javax.inject.Inject
import sourabh.pal.mysocial.common.data.api.ConnectionManager
import sourabh.pal.mysocial.common.domain.NetworkUnavailableException

class NetworkStatusInterceptor @Inject constructor(private val connectionManager: ConnectionManager) {
    val plugin = createClientPlugin("NetworkStatusInterceptor") {
        onRequest { _, _ ->
            if (!connectionManager.isConnected) {
                throw NetworkUnavailableException()
            }
        }
    }
}
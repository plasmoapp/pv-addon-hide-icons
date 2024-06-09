package su.plo.voice.hideicons

import su.plo.voice.api.addon.AddonInitializer
import su.plo.voice.api.addon.AddonLoaderScope
import su.plo.voice.api.addon.InjectPlasmoVoice
import su.plo.voice.api.addon.annotation.Addon
import su.plo.voice.api.event.EventSubscribe
import su.plo.voice.api.server.PlasmoVoiceServer
import su.plo.voice.api.server.audio.source.ServerEntitySource
import su.plo.voice.api.server.audio.source.ServerPlayerSource
import su.plo.voice.api.server.audio.source.ServerStaticSource
import su.plo.voice.api.server.event.audio.source.ServerSourceCreatedEvent
import su.plo.voice.api.server.event.config.VoiceServerConfigReloadedEvent

@Addon(
    id = "pv-addon-hide-icons",
    version = BuildConstants.VERSION,
    authors = ["Apehum"],
    scope = AddonLoaderScope.SERVER
)
class HideIconsAddon : AddonInitializer {

    @InjectPlasmoVoice
    private lateinit var voiceServer: PlasmoVoiceServer

    private lateinit var config: AddonConfig

    override fun onAddonInitialize() {
        loadConfig()
    }

    @EventSubscribe
    fun onConfigReloaded(event: VoiceServerConfigReloadedEvent) {
        loadConfig()
    }

    @EventSubscribe
    fun onSourceCreated(event: ServerSourceCreatedEvent) {
        val source = event.source

        if (config.hideEntityIcons && source is ServerEntitySource) {
            source.isIconVisible = false
        } else if (config.hideStaticIcons && source is ServerStaticSource) {
            source.isIconVisible = false
        } else if (config.hideStaticIcons && source is ServerPlayerSource) {
            source.isIconVisible = false
        }
    }

    private fun loadConfig() {
        config = AddonConfig.loadConfig(voiceServer)
    }
}

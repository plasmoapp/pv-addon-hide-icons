package su.plo.voice.hideicons

import su.plo.config.Config
import su.plo.config.ConfigField
import su.plo.config.provider.ConfigurationProvider
import su.plo.config.provider.toml.TomlConfiguration
import su.plo.voice.api.server.PlasmoVoiceServer
import java.io.File

@Config
class AddonConfig {

    @ConfigField
    val hideStaticIcons: Boolean = true

    @ConfigField
    val hidePlayerIcons: Boolean = true

    @ConfigField
    val hideEntityIcons: Boolean = true

    companion object {

        private val toml = ConfigurationProvider.getProvider<ConfigurationProvider>(TomlConfiguration::class.java)

        fun loadConfig(server: PlasmoVoiceServer): AddonConfig {

            val addonFolder = File(server.minecraftServer.configsFolder, "pv-addon-hide-icons")
            val configFile = File(addonFolder, "config.toml")

            return toml.load<AddonConfig>(AddonConfig::class.java, configFile, false)
                .also { toml.save(AddonConfig::class.java, it, configFile) }
        }
    }
}

package com.willfp.libreforge.triggers.triggers

import com.willfp.eco.core.integrations.mcmmo.McmmoManager
import com.willfp.libreforge.triggers.Trigger
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import com.willfp.libreforge.triggers.GenericCancellableEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemDamageEvent

class TriggerDamageItem : Trigger(
    "damage_item", listOf(
        TriggerParameter.PLAYER,
        TriggerParameter.LOCATION,
        TriggerParameter.EVENT,
        TriggerParameter.ITEM
    )
) {
    @EventHandler(ignoreCancelled = true)
    fun handle(event: PlayerItemDamageEvent) {
        if (McmmoManager.isFake(event)) {
            return
        }

        val player = event.player

        this.processTrigger(
            player,
            TriggerData(
                player = player,
                location = player.location,
                item = event.item,
                event = GenericCancellableEvent(event)
            ),
            event.damage.toDouble()
        )
    }
}

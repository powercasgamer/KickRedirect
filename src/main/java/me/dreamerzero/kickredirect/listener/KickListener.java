package me.dreamerzero.kickredirect.listener;

import java.util.stream.Stream;

import com.velocitypowered.api.event.Continuation;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.KickedFromServerEvent;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import me.dreamerzero.kickredirect.KickRedirect;
import me.dreamerzero.kickredirect.enums.CheckMode;
import me.dreamerzero.kickredirect.utils.ServerUtils;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public final class KickListener {
    private final KickRedirect plugin;

    public KickListener(KickRedirect plugin){
        this.plugin = plugin;
    }

    private static final PlainTextComponentSerializer SERIALIZER = PlainTextComponentSerializer.plainText();

    @Subscribe(order = PostOrder.LAST)
    public void onKickFromServer(KickedFromServerEvent event, Continuation continuation){
        if(!event.getResult().isAllowed()){
            continuation.resume();
            return;
        }
        event.getServerKickReason().map(SERIALIZER::serialize).ifPresent(reason -> {
            Stream<String> stream = plugin.config().getMessagesToCheck().stream();
            if(plugin.config().checkMode() == CheckMode.WHITELIST
                ? stream.anyMatch(reason::contains)
                : stream.noneMatch(reason::contains)
            ) {
                final RegisteredServer server = ServerUtils.getConfigServer(plugin);
                if(server == null) {
                    plugin.getLogger().error("No servers were found to redirect the player to");
                    final String kickMessage = plugin.messages().kickMessage();
                    if(!kickMessage.isBlank())
                        event.setResult(
                            KickedFromServerEvent.DisconnectPlayer.create(
                                KickRedirect.MINIMESSAGE.deserialize(kickMessage)
                            )
                        );
                    return;
                }
                event.setResult(KickedFromServerEvent.RedirectPlayer.create(server));
            }
        });
        continuation.resume();
    }
}

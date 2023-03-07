package io.github._4drian3d.kickredirect.listener.objects;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.NotNull;

import com.velocitypowered.api.permission.Tristate;
import com.velocitypowered.api.plugin.PluginManager;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.config.ProxyConfig;
import com.velocitypowered.api.proxy.messages.ChannelRegistrar;
import com.velocitypowered.api.proxy.player.ResourcePackInfo;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import com.velocitypowered.api.scheduler.ScheduledTask;
import com.velocitypowered.api.scheduler.Scheduler;
import com.velocitypowered.api.util.ProxyVersion;

import net.kyori.adventure.text.Component;

public class TestProxyServer implements ProxyServer {
    private final List<RegisteredServer> servers = List.of(
        new TestRegisteredServer(5).name("lobby1"),
        new TestRegisteredServer(0).name("lobby2"));

    @Override
    public RegisteredServer createRawRegisteredServer(ServerInfo arg0) {
        return null;
    }

    @Override
    public ResourcePackInfo.Builder createResourcePackBuilder(String arg0) {
        return null;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return Set.of();
    }

    @Override
    public Collection<RegisteredServer> getAllServers() {
        return servers;
    }

    @Override
    public InetSocketAddress getBoundAddress() {
        return null;
    }

    @Override
    public ChannelRegistrar getChannelRegistrar() {
        return null;
    }

    @Override
    public TestCommandManager getCommandManager() {
        return TestCommandManager.INSTANCE;
    }

    @Override
    public ProxyConfig getConfiguration() {
        return null;
    }

    @Override
    public ConsoleCommandSource getConsoleCommandSource() {
        return st -> Tristate.TRUE;
    }

    @Override
    public TestEventManager getEventManager() {
        return TestEventManager.INSTANCE;
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        return Optional.of(new TestPlayer(name, false));
    }

    @Override
    public Optional<Player> getPlayer(UUID arg0) {
        return Optional.of(new TestPlayer("name", false));
    }

    @Override
    public int getPlayerCount() {
        return 69;
    }

    @Override
    public PluginManager getPluginManager() {
        return TestPluginManager.INSTANCE;
    }

    @Override
    public Scheduler getScheduler() {
        return new Scheduler(){
            @Override
            public TaskBuilder buildTask(@NotNull Object plugin, @NotNull Runnable consumer) {
                return new TaskBuilder() {

                    @Override
                    public TaskBuilder delay(@IntRange(from = 0) long time, @NotNull TimeUnit unit) {
                        return this;
                    }

                    @Override
                    public TaskBuilder repeat(@IntRange(from = 0) long time, @NotNull TimeUnit unit) {
                        return this;
                    }

                    @Override
                    public TaskBuilder clearDelay() {
                        return this;
                    }

                    @Override
                    public TaskBuilder clearRepeat() {
                        return this;
                    }

                    @Override
                    public ScheduledTask schedule() {
                        return null;
                    }
                    
                };
            }

            @Override
            public TaskBuilder buildTask(@NotNull Object plugin, @NotNull Consumer<ScheduledTask> consumer) {
                return null;
            }

            @Override
            public @NotNull Collection<ScheduledTask> tasksByPlugin(@NotNull Object plugin) {
                return Collections.emptyList();
            }
        };
    }

    @Override
    public Optional<RegisteredServer> getServer(String arg0) {
        return servers.stream()
                .filter(sv -> sv.getServerInfo().getName().equals(arg0))
                .findAny();
    }

    @Override
    public ProxyVersion getVersion() {
        return new ProxyVersion("asd", "Peruviankkit", "3.1.2");
    }

    @Override
    public Collection<Player> matchPlayer(String arg0) {
        return Set.of();
    }

    @Override
    public Collection<RegisteredServer> matchServer(String arg0) {
        return Set.of();
    }

    @Override
    public RegisteredServer registerServer(ServerInfo arg0) {
        return null;
    }

    @Override
    public void shutdown() {
    }

    @Override
    public void shutdown(Component arg0) {
    }

    @Override
    public void unregisterServer(ServerInfo arg0) {
    }
}

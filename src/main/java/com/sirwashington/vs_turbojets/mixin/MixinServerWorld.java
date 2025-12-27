package com.sirwashington.vs_turbojets.mixin;

import com.sirwashington.vs_turbojets.network.NetworkManager;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;



@Mixin(net.minecraft.server.level.ServerLevel.class)
public abstract class MixinServerWorld {

    @Shadow
    public abstract ServerLevel getLevel();

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    public void tick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        System.out.println("initiated mixin tick");
        ServerLevel.class.cast(this);
        NetworkManager.TickNetworks(this.getLevel());
    }

}

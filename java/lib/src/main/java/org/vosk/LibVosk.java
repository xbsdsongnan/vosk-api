package org.vosk;

import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

public class LibVosk {

    static {
        // We have to load dependencies
        if (Platform.isWindows()) {
            Native.loadLibrary("libgcc_s_seh-1", LibVosk.class);
            Native.loadLibrary("libstdc++-6", LibVosk.class);
            Native.loadLibrary("libwinpthread-1", LibVosk.class);
        }
        Native.register(LibVosk.class, Platform.isWindows() ? "libvosk" : "vosk");
    }

    public static native void vosk_set_log_level(int level);

    public static native Pointer vosk_model_new(String path);

    public static native void vosk_model_free(Pointer model);

    public static native Pointer vosk_spk_model_new(String path);

    public static native void vosk_spk_model_free(Pointer model);

    public static native Pointer vosk_recognizer_new(Model model, float sample_rate);

    public static native Pointer vosk_recognizer_new_spk(Pointer model, Pointer spkModel, float sample_rate);

    public static native Pointer vosk_recognizer_new_grm(Pointer model, float sample_rate, String grammar);

    public static native boolean vosk_recognizer_accept_waveform(Pointer recognizer, byte[] data, int len);

    public static native boolean vosk_recognizer_accept_waveform_s(Pointer recognizer, short[] data, int len);

    public static native boolean vosk_recognizer_accept_waveform_f(Pointer recognizer, float[] data, int len);

    public static native String vosk_recognizer_result(Pointer recognizer);

    public static native String vosk_recognizer_final_result(Pointer recognizer);

    public static native String vosk_recognizer_partial_result(Pointer recognizer);

    public static native void vosk_recognizer_free(Pointer recognizer);

    public static void setLogLevel(LogLevel loglevel) {
        vosk_set_log_level(loglevel.getValue());
    }
}

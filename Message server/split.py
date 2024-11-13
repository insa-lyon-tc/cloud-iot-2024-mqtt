import wave
import os

def split_wav_file(file_path, chunk_size=256 * 1024):
   
    os.makedirs('audio', exist_ok=True)
    

    with wave.open(file_path, 'rb') as wav_file:
        n_channels, sampwidth, framerate, n_frames, comptype, compname = wav_file.getparams()
        
       
        frame_size = n_channels * sampwidth
        num_chunks = n_frames * frame_size // chunk_size
        
        for i in range(num_chunks + 1):
            
            start_frame = i * (chunk_size // frame_size)
            wav_file.setpos(start_frame)
            frames = wav_file.readframes(chunk_size // frame_size)
            
          
            with wave.open(f'audio/chunk_{i + 1}.wav', 'wb') as chunk_file:
                chunk_file.setparams((n_channels, sampwidth, framerate, 0, comptype, compname))
                chunk_file.writeframes(frames)

split_wav_file('test.wav')

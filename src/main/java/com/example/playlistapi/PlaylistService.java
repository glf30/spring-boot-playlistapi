package com.example.playlistapi;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepo;

    public PlaylistService(PlaylistRepository playlistRepo) {
        this.playlistRepo = playlistRepo;
    }

    public List<Playlist> getAllPlaylists() {
        //grab all playlists from database - we get this method from our repository (set up by JPA dependency)
        return playlistRepo.findAll();
    }

    public Playlist addPlaylist(Playlist playlist){
        // saves playlist to database
        return playlistRepo.save(playlist);
    }

    public Optional<Playlist> getPlaylistById(Long id) {
        return playlistRepo.findById(id);
    }

    public Optional<Playlist> updatePlaylist(Long id, Playlist updatedPlaylist){
        // map over (or overwrite) the existing playlist data with the updatedPlaylist data
        return playlistRepo.findById(id).map(existing -> {
            existing.setName(updatedPlaylist.getName());
            existing.setDescription(updatedPlaylist.getDescription());
            return playlistRepo.save(existing);
        });
    }

    public boolean deletePlaylist(Long id){
        if (playlistRepo.existsById(id)) {
            playlistRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Playlist addSongToPlaylist(Long playlistId, Song song){
        Playlist playlist = playlistRepo.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        playlist.addSong(song);
        return playlistRepo.save(playlist);
    }
}

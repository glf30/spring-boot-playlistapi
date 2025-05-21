package com.example.playlistapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService service;

    public PlaylistController(PlaylistService service) {
        this.service = service;
    }

    @GetMapping
    public List<Playlist> getPlaylists(){
        //return List.of(new Playlist("Summer jamz"), new Playlist("best of 2025", "2025 jamz"));
        return service.getAllPlaylists();
    }

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist){
        Playlist savedPlaylist = service.addPlaylist(playlist);
        return new ResponseEntity<>(savedPlaylist, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id){
        Optional<Playlist> playlist = service.getPlaylistById(id);

        return playlist
                .map(ResponseEntity::ok) //if we get back data, return our HTTP statuscode and data
                .orElseGet(() -> ResponseEntity.notFound().build()); // return not found
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable Long id, @RequestBody Playlist updatedPlaylist){
        Optional<Playlist> playlist = service.updatePlaylist(id, updatedPlaylist);

        return playlist
                .map(ResponseEntity::ok) //if we get back data, return our HTTP statuscode and data
                .orElseGet(() -> ResponseEntity.notFound().build()); // return not found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id){
        boolean deleted = service.deletePlaylist(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{playlistId}/songs")
    public ResponseEntity<Playlist> addSongToPlaylist( @PathVariable Long playlistId, @RequestBody Song song){
        Playlist updatedPlaylist = service.addSongToPlaylist(playlistId, song);
        return ResponseEntity.ok(updatedPlaylist);
    }
}

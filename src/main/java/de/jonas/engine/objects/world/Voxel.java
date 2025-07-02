package de.jonas.engine.objects.world;

public class Voxel {
    private String voxelID;

    public Voxel(String voxelID) {
        this.voxelID = voxelID;
    }

    public String getVoxelID() {return voxelID;}

    public void setVoxelID(String voxelID) {this.voxelID = voxelID;}
}

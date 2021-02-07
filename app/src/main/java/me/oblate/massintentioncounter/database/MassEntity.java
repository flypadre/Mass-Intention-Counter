package me.oblate.massintentioncounter.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mass_intentions")
public class MassEntity {
    @PrimaryKey(autoGenerate = true)
    public int key;

    @ColumnInfo(name = "mass_intention")
    private final String massIntention;

    @ColumnInfo(name = "reported")
    private final boolean reported;

    @ColumnInfo(name = "date_mass_said")
    private final int massDate;

    public MassEntity(String massIntention, boolean reported, int massDate) {
        this.massIntention = massIntention;
        this.reported = reported;
        this.massDate = massDate;
    }

    public String getMassIntention() {
        return massIntention;
    }

    public boolean isReported() {
        return reported;
    }

    public int getMassDate() {
        return massDate;
    }
}

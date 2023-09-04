package skills.examples.data.model;

public class Level {
    private String projectId;
    private String skillId;
    private int level;
    private int percent;
    private int pointsFrom;
    private int pointsTo;
    private String iconClass;
    private String name;
    private boolean achievable;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public int getPointsFrom() {
        return pointsFrom;
    }

    public void setPointsFrom(int pointsFrom) {
        this.pointsFrom = pointsFrom;
    }

    public int getPointsTo() {
        return pointsTo;
    }

    public void setPointsTo(int pointsTo) {
        this.pointsTo = pointsTo;
    }

    public boolean isAchievable() {
        return achievable;
    }

    public void setAchievable(boolean achievable) {
        this.achievable = achievable;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }
}


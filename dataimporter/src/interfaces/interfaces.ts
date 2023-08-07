export interface Project {
    id: string;
    subjects: Subject[];
    badges: Badge[];
    name: string;
}

export interface Subject {
    id: string;
    name: string;
    iconClass: string;
    skills: Skill[];
}

export interface Skill {
    id: string;
    name: string;
    description: string;
    selfReportingType?: string;
    helpUrl?: string;
}

export interface Badge {
    id: string;
    name: string;
    description: string;
    iconClass: string;
    skillIds: string[];
    gem: boolean;
    shouldAdminAchieve: boolean;
}
